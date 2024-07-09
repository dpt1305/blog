# Get the number of items in the table using the data source
# data "aws_dynamodb_table" "dynamo_db_table" {
#   table_name = local.name
# }
# data "aws_dynamodb_table" "dynamodb_table" {
#     name = local.name
# }

# Define the CloudWatch metric
# resource "aws_cloudwatch_metric" "item_count_metric" {
#   namespace = "AWS/DynamoDB"
#   metric_name = "ItemCount"
#   dimensions = {
#     TableName = aws_dynamodb_table.dynamodb_table.name
#   }
# }

# Create the CloudWatch alarm
resource "aws_cloudwatch_metric_alarm" "item_count_alarm" {
    
  alarm_name = "High-Item-Count-In-${aws_dynamodb_table.Users.name}"
  alarm_description = "Alarm triggered if item count in ${aws_dynamodb_table.Users.name} exceeds 10"
  comparison_operator = "GreaterThanOrEqualToThreshold"
  threshold = 2
  evaluation_periods = 5  # Evaluate over 2 periods (e.g., 2 minutes)
  statistic = "Sum"
  period = 60            # Period of the metric data (e.g., 1 minute)
  namespace = "AWS/DynamoDB"
  metric_name = "SuccessfulRequestLatency"
  dimensions = {
    TableName = aws_dynamodb_table.Users.name
  }


  # Alarm actions (optional)
  # You can configure SNS notifications or Lambda function invocation here
  actions_enabled     = "true"
  alarm_actions       = [aws_sns_topic.sns_dynamo_trigger.arn]
}