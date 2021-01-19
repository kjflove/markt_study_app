# markt_study_app
 web app for management of study marketing
 
 # Installation
 1. Install spring tools suite 4 (https://spring.io/tools) and import the project in a new spring boot project
 2. Install a database management system ( beste choice => MySQL, https://www.mysql.com/de/downloads/)
    and create a databse with name "bachelor". then execute the /bachelor_project/bachelor.sql in the database to create all table.
 3. you can install phpMyAdmin to administer the database through the browser (https://www.phpmyadmin.net/downloads/)
 
 # Configuration
 1. datasource of database
   open the file ~/../resources/application.properties and set the acess data of your database
      -spring.datasource.username = newuser
      -spring.datasource.password = password
2. Setting path of upload folder
   open the file ../main/java/markt_study/storage/StorageProperties.java and give you folder location to storage the file
     /*private String location = "path_of_your_folder_location/upload-dir";
     
 # start the app
  1. run the app as Spring boot app
  2. start the url http://localhost:8080/login
  3. log in as "admin" or "supervisor" or "management" or "direction". (passwort "admin" for all user)
    you can add new user in admin-account.
 
  # application functions
  1. *user as admin
    - add a new user
    - show all user
  2. *user as direction
    - add and show the study
    - add and show the cient
    - show the questioner and answering
    - upload and download report
    - generate the pdf-file of study overview
  3. *user as management
    - add and show the questionner and answering
    - show the client
    - upload and download the report
  4. *user as supervisor
    - deploy the questioner and answering
    - show the questioner and answering
    - generate the pdf-file of questioner payslip
          
   
   
 
    
