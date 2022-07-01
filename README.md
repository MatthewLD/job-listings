# Job Lister Application
This application helps you post and list available jobs, giving their names and locations the users can search by.

## Configuration
The application was developed by Java 11 in Spring Boot. You need to have Java 11 installed on your computer to run.

IntelliJ IDEA 2021.3.3 Community Edition was used for development.

### How to run?
Open a Command Line within "target" folder and type the following command:
```
java -jar joblisterapp-1.0-SNAPSHOT.jar
```
The application will log on the command line, but as soon as it writes "Started JobListerApplication" it is ready to use.

### How to use?
You need a browser to use the application. For accessing the User Interface, type the following link in your browser:
```
http://localhost:8080/swagger-ui.html
```
The application uses Swagger UI to show you the operations you can do within the application. If you want to use any of them, click on the "Show/Hide" button next to its name.

## Services
There are currently 4 services implemented in the application, where you can:
- Register a new client;
- Register a new position;
- List position based on your search criterias;
- List an exact position based on its ID.

### Register a new client (client-registration-controller, /client)
You can register yourself as a client. This is obligatory in order to register new positions or to list all available positions.

__**Requirements:**__
- **Name:** Your name. It cannot be longer than 100 characters.
- **Email address:** Your email address. It must be unique (meaning that one email address can be used only by one client), and it must be a valid address.

If you enter any of these incorrectly, the application will let you know it in the response. Otherwise it will return an API-key which gives you access to the other services.

### Register a new position (position-registration-controller, /position)
You can register a new position.

__**Requirements:**__
- **Position name:** The name of the position. It cannot be longer than 50 characters.
- **Location:** The geographical location of the position. It cannot be longer than 50 characters.
- **API-key:** Your API-key. If you do not have an API-key, then register yourself as a client. It must be a valid API-key.

If your API-key cannot be found in the database, the application will let you know that. Otherwise it checks the validity of the name and location of the position. If both are correct, the application stores it in the database and returns the link of the position.

### List position based on your search criterias (position-search-controller, /position/search)
You can browse the available positions based on the criterias you set.

__**Requirements:**__
- **Key word:** The key word of your search. It cannot be longer than 50 characters.
- **Location:** The location of the position. If you leave it blank, the application will list every position based on the key word. It cannot be longer than 50 characters.
- **API-key:** Your API-key. If you do not have an API-key, then register yourself as a client. It must be a valid API-key.

The application will search for positions based on your criterias. If any positions found, the application will return a list of links, which refers to the correct position.

### List an exact position based on its ID (position-search-controller, /position/search/{id})
You can list all details of a position.

__**Requirements:**__
- **ID:** The identification number of the position.

If found, the application will return:
- the identification number of the position;
- the name of the position;
- the location of the position;
- the identification number of the client who registered the position.

## Possible ways for further development
There are several opportunities to expand the application, which are listed below:
### Services
- **Change data of client:** Clients should have a method to change the data of their registered client user. For example if the email address or the name changes, the user could change these using his / her API-key.
- **Change data of position:** If a client registers a position, but any of the given information changes, he / she could change the name, or the location. For this operation the client needs to give the identification number of the position and his / her valid API-key.
- **More information for a position:** More datas could be set for a position. Examples:
  - the roles and responsibilities of applicants;
  - requirements of the position;
  - the e-mail address of the client who registered the position so the applicant can apply for the position;
  - additional description.

### Backend
- **Client roles:** There could be roles for clients. They could be admins, guests, regular users, and so on. Admins could access even the database directly, guests could only see a "register yourself as a client" option and regular users could see their own registered positions.
- **Log in function:** If you register yourself as a client, you could be able to log in with your e-mail address and your password so you do not have to have your API-key in your clipboard. For this function the client registration service needs to be extended so clients can also set their password.
