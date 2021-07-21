# dth-service

## Maven Info ##
* Group ID: com.sample.project
* Artifact ID: dth-service

This project contains the required API's to manage users, their channel subscription and billing of the DTH provider. 

**Project Developers/maintainers**: S, Sindhu\
**Email address**: sindhushree27@gmail.com


## List of features ##
1. Admins can add a new user with user details.
2. Retrieves list of channels available.
3. Rertieves user details by user identifier. By default retrieves all user details.
4. Subscribe to a channel using channel identifier.
5. Unsubscribe to a channel using channel identifier.
6. Retrieves list of active subscribtions for a user.
7. Automatically generates monthly bill at the beginning of every month and exports it to csv file.
8. Supports Basic OAuth.
9. Error handling for all endpoints.
10. Swagger documenttaion for REST APIs\
    API doc available: /v2/api-docs
    
    Try it out with UI: /swagger-ui.html

## Pre-requisite ##
Ensure you have maven installed in your system. You can install it from https://maven.apache.org/

Also ensure maven path is set in you System so that you can run mvn commands.

## Run and build ##
1. Clone the project:\
    `https://github.com/SindhuSGit/dth-service.git`
2. The Application can be run using the following command\
    `mvn spring-boot:run`
3. Hit the endpoint from postman.

## Postman response for all scenarios of endpoints can be found here with screenshots
https://gist.github.com/SindhuSGit/fb0bc316c98d69272c70d786988dff1e

## REST APIs: ##

### 1. Get Channels ###
Request type: GET\
Request path: /dth-service/channels

Response: Retrives all the channels available.


### 2. Add User ###
Request type: POST\
Request path: /dth-service/add-user

Request Body:
```json
{
    "name": "Sindhu",
    "emailAddress": "sindhu@gmail.com",
    "mobileNumber": "456789876"
}
```
Response: true/false defining the status of the user added.


### 3. Get User details ###
Request type: GET\
Request path: /dth-service/user-details

Response: Retrives all the user details.

Request type: GET\
Request path: /dth-service/user-details?userId={userId}

Response: Retrives the user details for the passed userId.

### 4. Subscribe to channels ###
Request type: POST\
Request path: /dth-service/userId/subscriptions

Request Body:
```json
[
    List of channel ids/id
]
```
Response: 
```json
[
    {
        "channelId": 101,
        "subscriptionStatus": "SUBSCRIBED"
    }
]
```

### 5. Unsubscribe to channels ###
Request type: DELETE\
Request path: /dth-service/userId/subscriptions

Request Body:
```json
[
    List of channel ids/id
]
```
Response: 
```json
[
    {
        "channelId": 101,
        "subscriptionStatus": "UNSUBSCRIBED"
    }
]
```

### 6. Get active subscriptions ###
Request type: GET\
Request path: /dth-service/userId/subscriptions

Response: Retrives all the active subscription details corresponding to the user.
```json
[
    {
        "name": "Tens sports 2",
        "price": 21
    }
]
```
