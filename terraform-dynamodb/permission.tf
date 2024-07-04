resource "aws_iam_role" "cloudwatch_to_sns_role" {
  name = "cloudwatch_to_sns_role"

  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Effect": "Allow",
      "Principal": {
        "Service": "cloudwatch.amazonaws.com"
      }
    }
  ]
}
EOF
}

resource "aws_iam_policy" "cloudwatch_to_sns_policy" {
  name        = "cloudwatch_to_sns_policy"
  description = "Policy for CloudWatch to SNS"

  policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": [
        "sns:Publish"
      ],
      "Effect": "Allow",
      "Resource": "${aws_sns_topic.example.arn}"
    }
  ]
}
EOF
}

resource "aws_iam_role_policy_attachment" "attach_policy" {
  role       = aws_iam_role.cloudwatch_to_sns_role.name
  policy_arn = aws_iam_policy.cloudwatch_to_sns_policy.arn
}
