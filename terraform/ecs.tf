module "ecs" {
  source = "terraform-aws-modules/ecs/aws"

  cluster_name = local.name

  create_cloudwatch_log_group = false
  cluster_configuration = {
    execute_command_configuration = {
      logging = "OVERRIDE"
      log_configuration = {
        cloud_watch_log_group_name = "${local.name}-exec-logs"
      }
    }
  }

  default_capacity_provider_strategy = {
    FARGATE_SPOT = {
      weight = 100
    }
  }

  tags = merge(local.tags, {
    Name = local.name
  })
}

module "dogs_shipment_svc" {
  source = "terraform-aws-modules/ecs/aws//modules/service"

  cpu    = 512
  memory = 1024

  name        = local.dogs_shipment_svc
  cluster_arn = module.ecs.cluster_arn

  assign_public_ip     = true
  autoscaling_policies = {}
  enable_autoscaling   = false
  desired_count        = 1
  health_check_grace_period_seconds = 180

  enable_execute_command    = true
  tasks_iam_role_statements = local.tasks_iam_role_statements
  task_exec_iam_statements  = local.task_exec_iam_statements

  container_definitions = {
    otel-collector = {
      cpu       = 256
      memory    = 512
      image = "otel/opentelemetry-collector-contrib"
      portMappings = [
        {
          hostPorr = 2000
          containerPort = 2000
        },
        {
          containerPort = 4317
          hostPort = 4317
        },
        {
          containerPort = 4318
          hostPort = 4318
        },
        {
          containerPort = 13133
          hostPort = 13133
        }
      ],
      "command": ["--config", "env:SSM_CONFIG"],
      "secrets": [
        {
          "name": "SSM_CONFIG",
          "valueFrom": aws_ssm_parameter.otel_collector_config.name
        }
      ]
    }
    dogs_svc = {
      cpu       = 256
      memory    = 512
      essential = true
      image     = var.docker_image
      portMappings = [
        {
          name          = "port-8080"
          containerPort = 8080
          protocol      = "tcp"
        }
      ]
      environment = [
        {
          name  = "ENVIRONMENT"
          value = var.environment
        },
        {
          name = "OTEL_TRACES_EXPORTER",
          value = var.otel_exporter
        },
        {
          name  = "OTEL_EXPORTER_OTLP_TRACES_ENDPOINT"
          value = "http://localhost:4318/v1/traces"
        },
        {
          name = "OTEL_METRICS_EXPORTER",
          value = var.otel_exporter
        },
        {
          name = "OTEL_EXPORTER_OTLP_METRICS_ENDPOINT"
          value = "http://localhost:4318/v1/metrics"
        },
        {
          name = "OTEL_LOGS_EXPORTER",
          value = "none"
        },
      ]
      dependsOn = [
        {
          containerName = "otel-collector"
          condition     = "START"
        }
      ]

      healthCheck = {
        command      = ["CMD-SHELL", "curl -f http://localhost:8080/actuator/health || exit 1"]
        startPeriod = 120
      }

      # Example image used requires access to write to root filesystem
      readonlyRootFilesystem = false

      create_cloudwatch_log_group = false
      logConfiguration = {
        "logDriver" : "awslogs",
        "options" : {
          "awslogs-group" : aws_cloudwatch_log_group.dogs_shipment_svc.name,
          "awslogs-region" : "us-east-1",
          "awslogs-stream-prefix" : "ecs"
        }
      }
    }
  }

  load_balancer = {
    service = {
      target_group_arn = module.alb.target_groups["ex_ecs"].arn
      container_name   = "dogs_svc"
      container_port   = 8080
    }
  }

  subnet_ids = module.vpc.private_subnets

  security_group_ingress_rules = {
    ingress_port_8080 = {
      type        = "ingress"
      from_port   = 8080
      to_port     = 8080
      protocol    = "TCP"
      cidr_ipv4   = "0.0.0.0/0"
      description = "Allow traffic on ingress 8080"
    }
  }

  security_group_egress_rules = {
    egress_all = {
      type      = "egress"
      from_port = 443
      to_port   = 443
      protocol  = "-1"
      cidr_ipv4 = "0.0.0.0/0"
    }
  }

  tags = merge(local.tags, {
    Name = local.dogs_shipment_svc
  })
}

resource "aws_cloudwatch_log_group" "dogs_shipment_svc" {
  name              = "${local.name}-logs"
  retention_in_days = 30
}

resource "aws_cloudwatch_log_group" "dogs_shipment_svc_exec" {
  name              = "${local.name}-exec-logs"
  retention_in_days = 30
}