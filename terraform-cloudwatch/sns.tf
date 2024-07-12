resource "aws_sns_topic" "sns_dynamo_trigger" {
  name                  = "sns-dynamo-trigger"
}
resource "aws_sns_topic_subscription" "send_email" {
  topic_arn = aws_sns_topic.sns_dynamo_trigger.arn
  protocol  = "email"
  endpoint  = "phutung99.kma@gmail.com"  # Replace with your verified email address
  endpoint_auto_confirms = true
}