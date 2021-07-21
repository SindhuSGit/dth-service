package com.sample.project.serviceprovider.manager;

import com.sample.project.serviceprovider.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link UserSubscriptionManager}
 */
class UserSubscriptionManagerTest {
    private UserSubscriptionManager userSubscriptionManager = new UserSubscriptionManager();
    private final User user = new User();

    /**
     * Test case to test the add user function. asserting true when a new user is added successfully.
     * @throws Exception throws Exception in case of empty values in the request.
     */
    @Test
    void addUserValidTest() throws Exception {
        user.setName("Test user");
        user.setMobileNumber("567890876578");
        user.setEmailAddress("user@gmail.com");
        assertTrue(userSubscriptionManager.addUser(user));
    }

    /**
     * Test case to test the add user function with empty values in which case an exception is expected.
     * @throws Exception throws Exception in case of empty values in the request.
     */
    @Test
    void addUserExceptionTest() throws Exception {
        User user = new User();
        user.setName("");
        user.setMobileNumber("");
        user.setEmailAddress("");
        Assertions.assertThrows(Exception.class, () -> {
            userSubscriptionManager.addUser(user);
        });
    }
}