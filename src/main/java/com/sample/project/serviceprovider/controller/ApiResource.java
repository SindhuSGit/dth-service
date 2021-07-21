package com.sample.project.serviceprovider.controller;

import com.sample.project.serviceprovider.manager.ChannelManager;
import com.sample.project.serviceprovider.manager.UserSubscriptionManager;
import com.sample.project.serviceprovider.response.ChannelResponse;
import com.sample.project.serviceprovider.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Resource class containing the API calls.
 * @author Sindhu S
 */
@RestController
@RequestMapping("/dth-service")
public class ApiResource {

    @GetMapping("/user-details")
    public @NonNull ResponseEntity getUserDetails(@RequestParam(value = "userId", required = false) String userId) throws Exception {
        try {
            UserSubscriptionManager userSubscriptionManager = new UserSubscriptionManager();
            return ResponseEntity.status(HttpStatus.OK).body(userSubscriptionManager.getUserList(userId));
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/channels")
    public @NonNull HashMap<String, ChannelResponse> getChannels() {
        return ChannelManager.getChannelList();
    }

    @GetMapping("/{userId}/subscriptions")
    public @NonNull ResponseEntity getSubscribedChannels(@PathVariable("userId") String userId) {
        try {
            UserSubscriptionManager userSubscriptionManager = new UserSubscriptionManager();
            return ResponseEntity.status(HttpStatus.OK).body(userSubscriptionManager.getSubscribedChannels(userId));
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{userId}/subscriptions")
    public @NonNull ResponseEntity subscribeChannel(@PathVariable("userId") String userId, @RequestBody List<Long> channels) throws Exception {
        try {
            UserSubscriptionManager userSubscriptionManager = new UserSubscriptionManager();
            return ResponseEntity.status(HttpStatus.OK).body(userSubscriptionManager.subscribeChannel(userId, channels));
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}/subscriptions")
    public @NonNull ResponseEntity unsubscribeChannel(@PathVariable("userId") String userId, @RequestBody List<Long> channels) throws Exception {
        try {
            UserSubscriptionManager userSubscriptionManager = new UserSubscriptionManager();
            return ResponseEntity.status(HttpStatus.OK).body(userSubscriptionManager.unSubscribeChannel(userId, channels));
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(value = "/add-user")
    public ResponseEntity addUser(@RequestBody User userRequest) {
        try {
            UserSubscriptionManager userSubscriptionManager = new UserSubscriptionManager();
            return ResponseEntity.status(HttpStatus.OK).body(userSubscriptionManager.addUser(userRequest));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
