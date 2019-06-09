The project is build with gradle.

Go to the project root folder and execute the following commands

Command to build gradle project
--> gradle clean build

command to run gradle project
--> gradle bootRun 

The server is running in port 9090.

The URL to access the services are as follows -

The APIs are secured with Spring security Basic Authentication having username and password as "Admin","Admin".
 
1. GET ALL users -
http://localhost:9090/api/users/

2. GET a user with User ID
http://localhost:9090/api/users/{userId}

3. Add a new User
Go to Postman and add username, password and status in JSON format in Body
http://localhost:9090/api/users/

4. Update any user 
Go to Postman and add id, username, password and status in JSON format in Body
http://localhost:9090/api/users/

5. Delete any user with User ID
http://localhost:9090/api/users/{userId}

Currently the database which is integrated is MySQL, however the connection details of H2 database is also provided.

Spring Security is added for Basic authentication which uses in Memory username, password and role.

Unit Test cases for Controller and Service Layer are provided.

SQL logs and Info Logs are also configured and are getting printed on console.