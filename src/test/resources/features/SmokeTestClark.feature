Feature: This is a Smoke Test case for Logged in User on Clark Web Application
	
  @SmokeTest
  Scenario Outline:  User Logs in the web application and assert desired fields
  
  			Given I am on Clark - The Insurance Manager Home Page
  			Then I Log In into the Web Application
  			Then In Vertrage tab I assert that there are "<NumberOfContract>" contracts with "<ContractStatus>"
  			Then In the tab Rente I assert that Dein Renteneinkommen ist above "<EuroAmount>" Euro
  			Then In the tab Bedarf I assert Du hast alle empfohlenen Produkte is displayed in the left column
  			Then I click on Clark jetzt empfehlen and invite a friend  "<EmailID>" and i assert the the message
  			And I Take a Screenshot
  			
  			
  			
  Examples:
  |NumberOfContract||ContractStatus|	|EuroAmount| |EmailID|
  		|3|			|Gut versichert|	|2400|		|akashmekal@mailinator.com|
  			