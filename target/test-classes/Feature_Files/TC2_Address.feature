@Address 
Feature: UserAddress 
Scenario Outline: User add the new home address 

	Given User add the headers and beares authorize 
	And User add the "<first_name>", "<last_name>", "<mobile>", "<appartment>", <state>, <city>, <country>, "<zipcode>", "<address>" and "<address-type>" 
	When User set the "POST" Request Type for addUserAddress 
	Then User verify the status code 200 
	And User verify the Address added message "Address added successfully" 
	
	Examples: 
		|first_name|last_name|mobile|appartment|state|city|country|zipcode|address|address-type|
		|santhose|Kumar|8009005662|Z apartment|33|3356|103|562635|3/511-1,compound|home|
		
		
		
Scenario Outline: User update the Residential userAddress 

	Given User add the headers and beares authorize 
	And User update the <address_id> "<first_name>", "<last_name>", "<mobile>", "<appartment>", <state>, <city>, <country>, "<zipcode>", "<address>" and "<address-type>" 
	When User set the "PUT" Request Type for updateUserAddress 
	Then User verify the status code 200 
	And User verify the Address updated message "Address updated successfully" 
	
	Examples: 
		|address_id|first_name|last_name|mobile|appartment|state|city|country|zipcode|address|address-type|
		|5937|santhose|Kumar|4665668992|ABC apartment|33|3356|103|562635|3/511-1,compound|office|
		
		
Scenario: User get the all addresses 

	Given User add the headers and beares authorize 
	And User set the 'GET' Request Type for getUserAddress 
	Then User verify the status code 200 
	And user verify the get all address message "OK" 
	
Scenario: User delete one of his  Address 

	Given User add the headers and beares authorize 
	When User add the address_id 5937 to delete 
	And User set the "DELETE" Request Type for deleteAddress 
	Then User verify the status code 200 
	And User verify the delete address message "Address deleted successfully" 
	
	