package com.sample.project.serviceprovider.manager;

import com.sample.project.serviceprovider.models.Channel;
import com.sample.project.serviceprovider.response.ChannelResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Class to manage the Channel details.
 * @author Sindhu S
 */
public class ChannelManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelManager.class);
    public static final HashMap<Long, Channel> CHANNEL_LIST = new HashMap<>(10);

    /**
     * Gets list of channels.
     * @return Hash map of Channel name and {@link ChannelResponse}
     */
    public @NonNull static HashMap<String, ChannelResponse> getChannelList(){
        LOGGER.trace("Fetching the list of channels.");
        HashMap<String, ChannelResponse> channelResponseList = new LinkedHashMap<>();
        Channel channel;
        ChannelResponse channelResponse;
        Long channelId;
        for(int i = 0; i < 10; i++){
            channel = new Channel();
            channel.setName("Tens sports "+ (i+1));
            channel.setPrice(20+i);
            channelId = (long) (100+i);
            CHANNEL_LIST.put(channelId, channel);
            channelResponse = new ChannelResponse();
            channelResponse.setId(channelId);
            channelResponse.setPrice(channel.getPrice());
            channelResponseList.put(channel.getName(), channelResponse);
        }
        return channelResponseList;
    }
}
