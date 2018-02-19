# etincellesFront
This website is the client side of a community website that contains a directory of members, a google calendar integration, a blog and an about us page.

To use this project, clone it then open Eclipse (or another editor) and click import > existing maven project.
You need to create 2 files for the project to work :
- application.properties that needs to be located at src/main/resources/ and needs to have this format : https://docs.google.com/document/d/1wi7tndrVfZF-jemODF85TxLUmVEyjWDSBx_kcLez5YY/edit?usp=sharing
- SecurityUtility.java that needs to be located at src/main/java/com/etincelles/utility/ and needs to have this format : https://docs.google.com/document/d/1gHzs4rGNN1D228e_Toa97wDWbmT7p0z5E5JjZ3rV2jM/edit?usp=sharing

Then you can right cick on the project and select "run as" > "spring boot app" and your app will be launched on http://localhost:8080

To package it into a .jar simply navigate to the folder in your terminal (you need to be in the foler that contains the pom.xml file) and type "mvn package"
