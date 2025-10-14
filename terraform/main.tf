locals {
  team        = var.team_name
  stack       = "dogs-shipment-svc"
  environment = var.environment
  name        = "${local.team}-${local.environment}-${local.stack}"
  tags = {
    team        = local.team
    stack       = local.stack
    environment = local.environment
  }

  dogs_shipment_svc = "${local.team}-${local.environment}-dogs-svc"

  tasks_iam_role_statements = [
    {
      actions   = ["ecs:ExecuteCommand"]
      effect    = "Allow"
      resources = [module.ecs.cluster_arn]
    },
    {
      actions = ["xray:PutTraceSegments"]
      effect  = "Allow"
      resources = ["*"]
    },
    {
      actions = [
        "logs:CreateLogGroup",
        "logs:CreateLogStream",
        "logs:PutLogEvents",
        "logs:DescribeLogStreams",
        "logs:DescribeLogGroups"
      ]
      effect  = "Allow"
      resources = ["*"]
    }
  ]
  task_exec_iam_statements = [
    {
      actions   = ["logs:CreateLogGroup"]
      effect    = "Allow"
      resources = ["*"]
    },
    {
      actions = ["ssm:GetParameter", "ssm:GetParameters"]
      effect  = "Allow"
      resources = [aws_ssm_parameter.otel_collector_config.arn]
    }
  ]
}

data "aws_availability_zones" "azs" {}

