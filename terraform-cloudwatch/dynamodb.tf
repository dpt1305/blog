locals  {
	name = "Users"
}


resource "aws_dynamodb_table" "Users" {
  name           =  local.name

	/**
	* billing_mode:
	*   + PROVISIONED: by read/write capacity
	*   + PAY_PER_REQUEST: 
	*/
  billing_mode   = "PROVISIONED"
  read_capacity  = 5
  write_capacity = 5
  hash_key       = "UserId"
  range_key      = "Username"

	attribute {
    name = "UserId"
    type = "S"
  }
	attribute {
    name = "Username"
    type = "S"
  }
	attribute {
		name = "PhoneNumber"
		type = "S"
	}

  ttl {
    attribute_name = "DeleteAfter"
    enabled        = true
  }


	/*
		non_key_attributes - (Optional) 
		Only required with INCLUDE as a projection type; 
		a list of attributes to project into the index. 
		These do not need to be defined as attributes on the table.
	*/
	# non_key_attributes {}

	/*
	*	ALL: projects every attribute into the index
	* KEYS_ONLY projects into the index only the table and index hash_key and sort_key attributes
	* INCLUDE projects into the index all of the attributes that are defined in non_key_attributes in addition to the attributes that that KEYS_ONLY project.
	*/
	local_secondary_index {
		name = "phone_index"
		range_key = "PhoneNumber"
		projection_type = "ALL"
	}

	tags = {
    Name        = "dynamodb-table-user"
    Environment = "dev"
  }

}