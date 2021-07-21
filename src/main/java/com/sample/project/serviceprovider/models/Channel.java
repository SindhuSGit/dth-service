package com.sample.project.serviceprovider.models;

/**
 * Model class for representing Channel.
 * @author Sindhu S
 */
public class Channel {
    private String name;
    private Integer price;

    /**
     * Getter for name of the channel.
     * @return Name of the channel.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the channel.
     * @param name Name of the channel.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for price of the channel.
     * @return Price of the channel.
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Setter for the price of the channel.
     * @param price Price of the channel.
     */
    public void setPrice(Integer price) {
        this.price = price;
    }
}
