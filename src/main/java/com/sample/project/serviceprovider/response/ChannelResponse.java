package com.sample.project.serviceprovider.response;

/**
 * Response class for representing Channel.
 * @author Sindhu S
 */
public class ChannelResponse {
    private Long id;
    private Integer price;

    /**
     * Getter for the channel identifier.
     * @return Channel identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for the channel identifier.
     * @param id Channel identifier.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the price of the channel.
     * @return Price of the channel.
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Setter for the price.
     * @param price Price of the channel.
     */
    public void setPrice(Integer price) {
        this.price = price;
    }
}
