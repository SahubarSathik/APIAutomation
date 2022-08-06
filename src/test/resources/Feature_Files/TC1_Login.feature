@Login 
Feature: TC1_Login OMRBranch API Automation with valid data 
Scenario: Get user logtoken from login endpoint 
	Given User add header 
	And User add basic authentication for login 
	When User send "POST" request for login endpoint 
	Then User verify the status code 200 
	And User verify the response body firstname present as "Sahubar" and get the logtoken saved 
	
	
	
	
	
 