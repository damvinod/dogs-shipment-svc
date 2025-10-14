variable "team_name" {
  description = "Required variable used for tagging, naming and isolating teams"
  default     = "merlion"
}

variable "environment" {
  description = "Required variable for isolating environments"
  default     = "dev"
}

variable "docker_image" {
  default = "vinodreddy25/dogs-shipment-svc:main"
}

variable "my_ip" {
  default = "58.182.91.66/32"
}

variable "otel_exporter" {
  default = "none" //To enable change it to "otlp"
}