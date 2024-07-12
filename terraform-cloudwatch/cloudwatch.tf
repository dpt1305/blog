# Create the CloudWatch alarm
# Docs: https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/AlarmThatSendsEmail.html
resource "aws_cloudwatch_metric_alarm" "item_count_alarm" {
  alarm_name = "Item-Count-In-${aws_dynamodb_table.Users.name}"
  alarm_description = "Alarm triggered if item count in ${aws_dynamodb_table.Users.name} more than 10"
  comparison_operator = "GreaterThanOrEqualToThreshold"
  threshold = 2
  evaluation_periods = 1  
  datapoints_to_alarm = 1
  period = 60            
  namespace = "AWS/DynamoDB"
  metric_name = "SuccessfulRequestLatency"
  dimensions = {
    TableName = aws_dynamodb_table.Users.name
    Operation = "GetItem"
  }
  statistic = "SampleCount"
  actions_enabled     = "true"
  alarm_actions       = [aws_sns_topic.sns_dynamo_trigger.arn]
}