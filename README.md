# service-provider-reactor

## Maven Info ##
* Group ID: com.sample.project
* Artifact ID: service-provider

This project contains the required API's to manage users, their channel subscription and billing of the DTH provider. 

**Project Developers/maintainers**: S, Sindhu

## REST APIS: ##

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

### 4. Subscribe to channels ###
Request type: POST\
Request path: http://localhost:8080/userId/subscriptions
Request Body:
```json
{
    "channelIds": [
        101
    ]
}
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
{
    "channelIds": [
        101
    ]
}
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
 
