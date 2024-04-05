resource "aws_instance" "public_ec2_instance" {
  ami           = "ami-0910e4162f162c238"
  instance_type = "t2.micro"
  subnet_id     = aws_subnet.public_subnet.id

  tags = {
    Name = "public_ec2_instance"
  }
}

resource "aws_instance" "private_ec2_instance" {
  ami           = "ami-0910e4162f162c238"
  instance_type = "t2.micro"
  subnet_id     = aws_subnet.private_subnet.id

  tags = {
    Name = "private_ec2_instance"
  }
}