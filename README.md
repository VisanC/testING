This application is used to provide authentication and user storage
for a potential web application. It uses the Spring Boot framework,
with Spring Security providing the authentication features.
The application uses a mySql database for persistent storage (although the 
application.properties file in which the database details are to be specified
is not present for security considerations). As data access is done 
using JPA, we can easily change the configuration to allow a different database type.

As per the requirements given, the application provides basic logging and unit tests,
as well as an attempt to use new Java features, in this case the new Switch expression 
introduced in java 14, used here in the UserController class.

List of endpoints:

/users - provides details for all the registered users. This endpoint
requires users to be authenticated to use


/register?userName&userEmail&password - provides the option to register 
the new user. All three parameters must be present. The user name and
email must be unique (not previously used in this application) and
the password must pass a strength check (8+ characters, at least a digit).
The password is stored in an encrypted format, using BCrypt4

/login - default web login providing authentication. The login will
be automatically refused for 5 minutes if there were 5 incorrect attempts
made.