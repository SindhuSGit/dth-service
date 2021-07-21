package com.sample.project.serviceprovider.controller;

import com.sample.project.serviceprovider.manager.ChannelManager;
import com.sample.project.serviceprovider.manager.UserSubscriptionManager;
import com.sample.project.serviceprovider.models.Channel;
import com.sample.project.serviceprovider.response.ChannelResponse;
import com.sample.project.serviceprovider.response.SubscriptionResponse;
import com.sample.project.serviceprovider.models.User;
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

/**
 * Resource class containing the API calls.
 * @author Sindhu S
 */
@RestController
@RequestMapping("/dth-service")
public class ApiResource {

    @GetMapping("/user-details")
    public @NonNull List<User> getUserDetails(@RequestParam(value = "userId", required = false) String userId) throws Exception {
        UserSubscriptionManager userSubscriptionManager = new UserSubscriptionManager();
        return userSubscriptionManager.getUserList(userId);
    }

    @GetMapping("/channels")
    public @NonNull HashMap<String, ChannelResponse> getChannels() {
        return ChannelManager.getChannelList();
    }

    @GetMapping("/{userId}/subscriptions")
    public @NonNull List<Channel> getSubscribedChannels(@PathVariable("userId") String userId) throws Exception {
        UserSubscriptionManager userSubscriptionManager = new UserSubscriptionManager();
        return userSubscriptionManager.getSubscribedChannels(userId);
    }

    @PostMapping("/{userId}/subscriptions")
    public @NonNull List<SubscriptionResponse> subscribeChannel(@PathVariable("userId") String userId, @RequestBody List<Long> channels) throws Exception {
        UserSubscriptionManager userSubscriptionManager = new UserSubscriptionManager();
        return userSubscriptionManager.subscribeChannel(userId, channels);
    }

    @DeleteMapping("/{userId}/subscriptions")
    public @NonNull List<SubscriptionResponse> unsubscribeChannel(@PathVariable("userId") String userId, @RequestBody List<Long> channels) throws Exception {
        UserSubscriptionManager userSubscriptionManager = new UserSubscriptionManager();
        return userSubscriptionManager.unSubscribeChannel(userId, channels);
    }

    @PostMapping(value = "/add-user")
    public boolean addUser(@RequestBody User userRequest) throws Exception {
        UserSubscriptionManager userSubscriptionManager = new UserSubscriptionManager();
        return userSubscriptionManager.addUser(userRequest);
    }
}
