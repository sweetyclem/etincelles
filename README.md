# etincellesFront
This website is the client side of a community website that contains a directory of members, a google calendar integration, a blog and an about us page.

To use this project, clone it then open Eclipse (or another editor) and click import > existing maven project.
You need to create 2 files for the project to work :
- application.properties that needs to be located at src/main/resources/ and needs to have this format : https://github.com/sweetyclem/etincelles/blob/master/application.properties%20sample.pdf 
- SecurityUtility.java that needs to be located at src/main/java/com/etincelles/utility/ and needs to have this format : https://github.com/sweetyclem/etincelles/blob/master/SecurityUtility.java%20sample.pdf 

Then you can right cick on the project and select "run as" > "spring boot app" and your app will be launched on http://localhost:8080

To package it into a .jar simply navigate to the folder in your terminal (you need to be in the foler that contains the pom.xml file) and type "mvn package"
