package com.sample.project.serviceprovider.manager;

import static com.sample.project.serviceprovider.manager.ChannelManager.CHANNEL_LIST;
import com.sample.project.serviceprovider.models.User;
import com.sample.project.serviceprovider.models.Channel;
import com.sample.project.serviceprovider.response.SubscriptionResponse;
import com.sample.project.serviceprovider.enums.SubscriptionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.NoSuchElementException;

/**
 * Class to manage user subscription.
 * @author Sindhu S
 */
public class UserSubscriptionManager {
    private final Logger logger = LoggerFactory.getLogger(UserSubscriptionManager.class);
    public static final HashMap<String, User> USERS_MAP = new HashMap<>();

    /**
     * Get List of users based on the user identifier passed. If User identifier matches with the list then the function
     * returns the corresponding details. If user identifier is not passed then by default all the user information is returned.
     * If user identifier is invalid then not found error is thrown.
     *
     * @param userIdentifier Unique user identifier.
     * @return List of {@link User} matching the identifier or list of all {@link User} information by default.
     * @throws NoSuchElementException in case of user not found.
     */
    public @NonNull List<User> getUserList(final String userIdentifier) {
        if(StringUtils.hasText(userIdentifier)){
            if(!CollectionUtils.isEmpty(USERS_MAP) && USERS_MAP.containsKey(userIdentifier)){
                return Collections.singletonList(USERS_MAP.get(userIdentifier));
            } else {
                logger.error("User with the identifier: "+userIdentifier+" not found.");
                throw new NoSuchElementException("User with the identifier: "+userIdentifier+" not found.");
            }
        } else {
            return new ArrayList<>(USERS_MAP.values());
        }
    }

    /**
     * Returns the list of subscribed channels corresponding to the user identifier passed.
     *
     * @param userIdentifier Unique user identifier.
     * @return List of subscribed {@link Channel} for the user.
     * @throws NoSuchElementException in case of user not found.
     * @throws IllegalArgumentException in case of invalid user identifier.
     */
    public @NonNull List<Channel> getSubscribedChannels(final String userIdentifier) {
        List<Channel> subscribedChannelList = null;
        if(!userIdentifier.isEmpty()) {
            subscribedChannelList = new ArrayList<>();
            if(!CollectionUtils.isEmpty(USERS_MAP) && USERS_MAP.containsKey(userIdentifier) && USERS_MAP.get(userIdentifier) != null){
                final List<Long> subscribedChannelIds = USERS_MAP.get(userIdentifier).getSubscribedChannels();
                if(!CollectionUtils.isEmpty(subscribedChannelIds)){
                    for(Long channelId : subscribedChannelIds){
                        subscribedChannelList.add(CHANNEL_LIST.get(channelId));
                    }
                    return subscribedChannelList;
                } else {
                    return subscribedChannelList;
                }
            } else {
                logger.error("User with the identifier: "+userIdentifier+" not found.");
                throw new NoSuchElementException("User with the identifier: "+userIdentifier+" not found.");
            }
        } else {
            throw new IllegalArgumentException("User identifier must be passed.");
        }
    }

    /**
     * Function to subscribe channel(s).
     *
     * @param userIdentifier Unique user identifier.
     * @param channelIds List of channel identifiers to subscribe.
     * @return List of {@link SubscriptionResponse} with subscription status of each channel.
     * @throws NoSuchElementException in case of user not found.
     * @throws IllegalArgumentException in case of invalid user identifier or if channel ids list is empty.
     */
    public @NonNull List<SubscriptionResponse> subscribeChannel(final String userIdentifier, final List<Long> channelIds) {
        List<SubscriptionResponse> subscriptionResponseList = new ArrayList<>();
        SubscriptionResponse subscriptionResponse;
        if(StringUtils.hasLength(userIdentifier)){
            if(!CollectionUtils.isEmpty(USERS_MAP) && USERS_MAP.containsKey(userIdentifier)) {
                if(!CollectionUtils.isEmpty(channelIds)){
                    List<Long> subscribedChannels = USERS_MAP.get(userIdentifier).getSubscribedChannels();
                    for(Long channelId : channelIds) {
                        if(CHANNEL_LIST.containsKey(channelId)){
                            if(subscribedChannels.contains(channelId)){
                                logger.info("The channel is already subscribed.");
                                subscriptionResponse = new SubscriptionResponse();
                                subscriptionResponse.setChannelId(channelId);
                                subscriptionResponse.setSubscriptionStatus(SubscriptionStatus.ALREADY_SUBSCRIBED);
                                subscriptionResponseList.add(subscriptionResponse);
                            } else {
                                logger.info("The channel is successfully subscribed.");
                                subscribedChannels.add(channelId);
                                subscriptionResponse = new SubscriptionResponse();
                                subscriptionResponse.setChannelId(channelId);
                                subscriptionResponse.setSubscriptionStatus(SubscriptionStatus.SUBSCRIBED);
                                subscriptionResponseList.add(subscriptionResponse);
                            }
                        } else{
                            logger.info("Invalid channel identifier passed.");
                            subscriptionResponse = new SubscriptionResponse();
                            subscriptionResponse.setChannelId(channelId);
                            subscriptionResponse.setSubscriptionStatus(SubscriptionStatus.INVALID_CHANNEL);
                            subscriptionResponseList.add(subscriptionResponse);
                        }
                    }
                    return subscriptionResponseList;
                } else {
                    logger.error("Channel list cannot be empty.");
                    throw new IllegalArgumentException("Channel list cannot be empty.");
                }
            } else{
                logger.error("User with the identifier: "+userIdentifier+" not found.");
                throw new NoSuchElementException("User with the identifier: "+userIdentifier+" not found.");
            }
        } else {
            logger.error("User identifier must be passed.");
            throw new IllegalArgumentException("User identifier must be passed.");
        }
    }

    /**
     * Function to unsubscribe channel(s).
     *
     * @param userIdentifier Unique user identifier.
     * @param channelIds List of channel identifiers to unsubscribe.
     * @return List of {@link SubscriptionResponse} with status of each channel.
     * @throws NoSuchElementException in case of user not found.
     * @throws IllegalArgumentException in case of invalid user identifier or if channel ids list is empty.
     */
    public @NonNull List<SubscriptionResponse> unSubscribeChannel(final String userIdentifier, final List<Long> channelIds) {
        List<SubscriptionResponse> subscriptionResponseList = new ArrayList<>();
        SubscriptionResponse subscriptionResponse;
        if(StringUtils.hasLength(userIdentifier)){
            if(!CollectionUtils.isEmpty(USERS_MAP) && USERS_MAP.containsKey(userIdentifier)) {
                if(!CollectionUtils.isEmpty(channelIds)){
                    List<Long> subscribedChannels = USERS_MAP.get(userIdentifier).getSubscribedChannels();
                    for(Long channelId : channelIds) {
                        if(CHANNEL_LIST.containsKey(channelId)) {
                            if(subscribedChannels.contains(channelId)){
                                logger.info("Successfully unsubscribed the channel.");
                                subscribedChannels.remove(channelId);
                                subscriptionResponse = new SubscriptionResponse();
                                subscriptionResponse.setChannelId(channelId);
                                subscriptionResponse.setSubscriptionStatus(SubscriptionStatus.UNSUBSCRIBED);
                                subscriptionResponseList.add(subscriptionResponse);
                            } else {
                                logger.info("The channel cannot be unsubscribed as it is not subscribed.");
                                subscriptionResponse = new SubscriptionResponse();
                                subscriptionResponse.setChannelId(channelId);
                                subscriptionResponse.setSubscriptionStatus(SubscriptionStatus.CURRENTLY_NOT_SUBSCRIBED);
                                subscriptionResponseList.add(subscriptionResponse);
                            }
                        }
                        else {
                            logger.info("Invalid channel identifier passed.");
                            subscriptionResponse = new SubscriptionResponse();
                            subscriptionResponse.setChannelId(channelId);
                            subscriptionResponse.setSubscriptionStatus(SubscriptionStatus.INVALID_CHANNEL);
                            subscriptionResponseList.add(subscriptionResponse);
                        }
                    }
                    return subscriptionResponseList;
                }
                else {
                    logger.error("Channel list cannot be empty.");
                    throw new IllegalArgumentException("Channel list cannot be empty.");
                }
            } else{
                logger.info("User with the identifier: "+userIdentifier+" not found.");
                throw new NoSuchElementException("User with the identifier: "+userIdentifier+" not found.");
            }
        } else {
            logger.error("User identifier must be passed.");
            throw new IllegalArgumentException("User identifier must be passed.");
        }
    }

    /**
     * Adds a new user with their information.
     *
     * @param newUser {@link User} details.
     * @return true/false based on whether a new user is added successfully.
     * @throws IllegalArgumentException in case of invalid user details.
     */
    public boolean addUser(final User newUser) {
        if(newUser != null){
            final String mobileNumber = newUser.getMobileNumber();
            final String emailAddress = newUser.getEmailAddress();
            final String name = newUser.getName();
            if(StringUtils.hasText(mobileNumber) && StringUtils.hasText(emailAddress) && StringUtils.hasText(name)) {
            String userId = UUID.randomUUID().toString();
            newUser.setId(userId);
            USERS_MAP.put(userId, newUser);
            return true;
        } else {
            logger.info("User details cannot be empty/null.");
            throw new IllegalArgumentException("User details cannot be empty/null.");
            }
        }
        return false;
    }
}
