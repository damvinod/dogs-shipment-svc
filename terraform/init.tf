terraform {
  backend "s3" {
    region       = "us-east-1"
    bucket       = "vinod-terraform-test-bucket"
    key          = "merlion/dev/dogs-shipment-svc/terraform.tfstate"
    use_lockfile = true
  }
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 6.0"
    }
  }
}

provider "aws" {
  region = "us-east-1"
}