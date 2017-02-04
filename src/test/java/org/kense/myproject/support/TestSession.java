package org.kense.myproject.support;

public abstract class TestSession {

    private UserCredentials userCredentials = new UserCredentials("", "");

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    public String getUsername() {
        return userCredentials.getUsername();
    }

    public String getPassword() {
        return userCredentials.getPassword();
    }
}
