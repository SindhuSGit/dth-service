package com.sample.project.serviceprovider.response;

import com.sample.project.serviceprovider.enums.SubscriptionStatus;

/**
 * Response class for representing Subscription.
 * @author Sindhu S
 */
public class SubscriptionResponse {
    private Long channelId;
    private SubscriptionStatus subscriptionStatus;

    /**
     * Getter for the channel identifier.
     * @return channel identifier.
     */
    public Long getChannelId() {
        return channelId;
    }

    /**
     * Setter for the channel identifier.
     * @param channelId Channel identifier.
     */
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    /**
     * Getter for subscription status of the channel.
     * @return {@link SubscriptionStatus}
     */
    public SubscriptionStatus getSubscriptionStatus() {
        return subscriptionStatus;
    }

    /**
     * Setter for subscription status of the channel.
     * @param subscriptionStatus {@link SubscriptionStatus}
     */
    public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }
}
