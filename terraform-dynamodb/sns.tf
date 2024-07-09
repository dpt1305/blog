resource "aws_sns_topic" "sns_dynamo_trigger" {
  name                  = "sns-dynamo-trigger"
  # fifo_topic                  = true
#   content_based_deduplication = true

#   tags = {
#     Name = "Lab_SNS_CloudWatch"
#   }
}
resource "aws_sns_topic_subscription" "send_email" {
  topic_arn = aws_sns_topic.sns_dynamo_trigger.arn
  protocol  = "email"
  endpoint  = "phutung99.kma@gmail.com"  # Replace with your verified email address
}