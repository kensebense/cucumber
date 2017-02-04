package org.kense.myproject.transforms;

import cucumber.api.Transformer;
import org.kense.myproject.support.UserCredentials;


public class RoleToUserCredentialsConverter extends Transformer<UserCredentials> {
    @Override
    public UserCredentials transform(String roleName) {

        UserCredentials userCredentials = new UserCredentials("", "");

        if (roleName == null || roleName.isEmpty()) {
            throw new IllegalArgumentException("The role name must not be empty");
        }

        if (roleName.equalsIgnoreCase("crew") || roleName.equalsIgnoreCase("admin")) {
            userCredentials = new UserCredentials("00446", "abc");
        }

        if (roleName.equalsIgnoreCase("ambassador")) {
            userCredentials = new UserCredentials("00123", "abc");
        }

        return userCredentials;
    }
}
