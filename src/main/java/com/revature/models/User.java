package com.revature.models;

import java.util.Objects;

public class User {

    private int ersUsersId;
    private String ersUsername;
    private String ersPassword;
    private String userFirstName;
    private String userLastname;
    private String userEmail;
    private UserRole userRole;

    public User() {
    }

    public User(int ersUsersId, String ersUsername, String ersPassword, String userFirstName, String userLastname, String userEmail, UserRole userRole) {
        this.ersUsersId = ersUsersId;
        this.ersUsername = ersUsername;
        this.ersPassword = ersPassword;
        this.userFirstName = userFirstName;
        this.userLastname = userLastname;
        this.userEmail = userEmail;
        this.userRole = userRole;
    }

    public int getErsUsersId() {
        return ersUsersId;
    }

    public void setErsUsersId(int ersUsersId) {
        this.ersUsersId = ersUsersId;
    }

    public String getErsUsername() {
        return ersUsername;
    }

    public void setErsUsername(String ersUsername) {
        this.ersUsername = ersUsername;
    }

    public String getErsPassword() {
        return ersPassword;
    }

    public void setErsPassword(String ersPassword) {
        this.ersPassword = ersPassword;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getErsUsersId() == user.getErsUsersId() && Objects.equals(getErsUsername(), user.getErsUsername()) && Objects.equals(getErsPassword(), user.getErsPassword()) && Objects.equals(getUserFirstName(), user.getUserFirstName()) && Objects.equals(getUserLastname(), user.getUserLastname()) && Objects.equals(getUserEmail(), user.getUserEmail()) && Objects.equals(userRole, user.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getErsUsersId(), getErsUsername(), getErsPassword(), getUserFirstName(), getUserLastname(), getUserEmail(), userRole);
    }

    @Override
    public String toString() {
        return "User{" +
                "ersUsersId=" + ersUsersId +
                ", ersUsername='" + ersUsername + '\'' +
                ", ersPassword='" + ersPassword + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastname='" + userLastname + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
