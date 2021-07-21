package com.sample.project.serviceprovider.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class for representing User.
 * @author Sindhu S
 */
public class User {
    private String id;
    private String name;
    private String mobileNumber;
    private String emailAddress;
    private List<Long> subscribedChannels = new ArrayList<>();

    /**
     * Getter for the identifier.
     * @return Unique identifier of the user.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the identifier.
     * @param id Unique identifier of the user.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the Name of the user.
     * @return Name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the Name of the user.
     * @param name Name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the email address.
     * @return Email address of the user.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Setter for the email address.
     * @param emailAddress Email address of the user.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Getter for the mobile number.
     * @return Mobile number of the user.
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Setter for the mobile number.
     * @param mobileNumber Mobile number of the user.
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Getter for the subscribed channels.
     * @return List of channels identifiers which are subscribed by the user.
     */
    public List<Long> getSubscribedChannels() {
        return subscribedChannels;
    }

    /**
     * Setter for the subscribed channels.
     * @param subscribedChannels List of channels identifiers which are subscribed by the user.
     */
    public void setSubscribedChannels(List<Long> subscribedChannels) {
        this.subscribedChannels = subscribedChannels;
    }
}
