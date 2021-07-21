# dth-service

## Maven Info ##
* Group ID: com.sample.project
* Artifact ID: dth-service

This project contains the required API's to manage users, their channel subscription and billing of the DTH provider. 

**Project Developers/maintainers**: S, Sindhu
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

## REST APIs: ##

### 1. Get Channels ###
Request type: GET\
Request path: http://localhost:8080/channels

Response: Retrives all the channels available.


### 2. Add User ###
Request type: POST\
Request path: http://localhost:8080/add-user

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
Request path: http://localhost:8080/user-details

Response: Retrives all the user details.

Request type: GET\
Request path: http://localhost:8080/user-details?userId={userId}

Response: Retrives the user details for the passed userId.

### 4. Subscribe to channels ###
Request type: POST\
Request path: http://localhost:8080/userId/subscriptions

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
Request path: http://localhost:8080/userId/subscriptions

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
Request path: http://localhost:8080/userId/subscriptions

Response: Retrives all the active subscription details corresponding to the user.
```json
[
    {
        "name": "Tens sports 2",
        "price": 21
    }
]
```

API doc available: http://localhost:8080/v2/api-docs

Try it out with UI: http://localhost:8080/swagger-ui.html
