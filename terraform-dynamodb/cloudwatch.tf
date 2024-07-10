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
# Docs: https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/AlarmThatSendsEmail.html
resource "aws_cloudwatch_metric_alarm" "item_count_alarm" {
    
  alarm_name = "Item-Count-In-${aws_dynamodb_table.Users.name}"
  alarm_description = "Alarm triggered if item count in ${aws_dynamodb_table.Users.name} more than 10"
  comparison_operator = "GreaterThanOrEqualToThreshold"
  threshold = 2
  evaluation_periods = 1  # Evaluate over 2 periods (e.g., 2 minutes)
  datapoints_to_alarm = 1

  period = 60            # Period of the metric data (e.g., 1 minute)
  namespace = "AWS/DynamoDB"
  metric_name = "ReturnedItemCount"
  dimensions = {
    TableName = aws_dynamodb_table.Users.name
    Operation = "Scan"
  }
  statistic = "Sum"

  # Alarm actions (optional)
  # You can configure SNS notifications or Lambda function invocation here
  actions_enabled     = "true"
  alarm_actions       = [aws_sns_topic.sns_dynamo_trigger.arn]
}