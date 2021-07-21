package com.sample.project.serviceprovider.manager;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.sample.project.serviceprovider.response.ChannelResponse;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;

/**
 * Test class for {@link ChannelManager}
 * @author Sindhu S
 */
public class ChannelManagerTest {
    /**
     * Test channel list and assert the size.
     */
    @Test
    public void getChannelList() {
        HashMap<String, ChannelResponse> channelResponseHashMap =  ChannelManager.getChannelList();
        assertTrue(!CollectionUtils.isEmpty(channelResponseHashMap));
        assertTrue(channelResponseHashMap.values().size() == 10);
    }
}