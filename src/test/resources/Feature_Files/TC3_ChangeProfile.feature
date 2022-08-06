@ChangeProfile 
Feature: Profile Picture 
Scenario: User Update Profile Picture 

	Given User add the headers and multiPart and beares authorize 
	And User add formData and Upload image file  
	Then User add request type as "POST" for ChangeProPic 
	And User verify the status code 200 
	And User verify the profile updated message as "Profile updated Successfully" 
