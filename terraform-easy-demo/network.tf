resource "aws_vpc" "terraform_demo_vpc" {
  cidr_block = "10.0.0.0/24"
  tags = {
    Name = "terraform_demo_vpc",
    Project = "blog_demo_terraform",
  }
}

# public subnet
resource "aws_subnet" "public_subnet" {
  vpc_id            = aws_vpc.terraform_demo_vpc.id
  cidr_block        = "10.0.0.0/25"
  availability_zone = "ap-southeast-1a"
  map_public_ip_on_launch = true

  tags = {
    Name = "public_subnet",
    Project = "blog_demo_terraform",
  }
}

# private subnet
resource "aws_subnet" "private_subnet" {
  vpc_id            = aws_vpc.terraform_demo_vpc.id
  cidr_block        = "10.0.0.128/25"
  availability_zone = "ap-southeast-1a"
  map_public_ip_on_launch = false  

  tags = {
    Name = "private_subnet",
    Project = "blog_demo_terraform",
  }
}

# internet gateway
resource "aws_internet_gateway" "public_access_igw" {
  vpc_id = aws_vpc.terraform_demo_vpc.id

  tags = {
    Name = "public_access_igw",
    Project = "blog_demo_terraform",
  }
}

# route table
resource "aws_route" "public_access_route_table" {
  route_table_id         = aws_vpc.terraform_demo_vpc.default_route_table_id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_internet_gateway.public_access_igw.id
}