resource "aws_ssm_parameter" "otel_collector_config" {
  name        = "${local.name}-otel-collector-config"
  type        = "String"
  value       = <<EOF
extensions:
  awsproxy:
    endpoint: 0.0.0.0:2000
  health_check:
    endpoint: 0.0.0.0:13133
    path: "/health"

receivers:
  otlp:
    protocols:
      grpc:
        endpoint: 0.0.0.0:4317
      http:
        endpoint: 0.0.0.0:4318

processors:
  batch:
  filter/byCrates:
    traces:
      span:
        - 'attributes["http.route"] == "/actuator/health"'

exporters:
  awsxray:
    region: 'us-east-1'

service:
  pipelines:
    traces:
      receivers: [otlp]
      processors: [filter/byCrates, batch]
      exporters: [awsxray]
  extensions: [awsproxy, health_check]
EOF
  tags = local.tags
}