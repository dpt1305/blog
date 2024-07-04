resource "aws_sns_topic" "sns_fifo_example" {
  name                  = "sns-example.fifo"
  fifo_topic                  = true
#   content_based_deduplication = true

#   tags = {
#     Name = "Lab_SNS_CloudWatch"
#   }
}
resource "aws_sns_topic_subscription" "example" {
  topic_arn = aws_sns_topic.sns_fifo_example.arn
  protocol  = "email"
  endpoint  = "phutung99.kma@gmail.com"  # Replace with your verified email address
}