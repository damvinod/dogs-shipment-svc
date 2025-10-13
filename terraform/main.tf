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
    }
  ]
  task_exec_iam_statements = [
    {
      actions   = ["logs:CreateLogGroup"]
      effect    = "Allow"
      resources = ["*"]
    }
  ]
}

data "aws_availability_zones" "azs" {}
