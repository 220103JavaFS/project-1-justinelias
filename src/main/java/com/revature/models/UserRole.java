package com.revature.models;

import java.util.Objects;

public class UserRole {

    private int ersUserRoleId;
    private String userRole;

    public UserRole() {
    }

    public UserRole(int ersUserRoleId, String userRole) {
        this.ersUserRoleId = ersUserRoleId;
        this.userRole = userRole;
    }

    public int getErsUserRoleId() {
        return ersUserRoleId;
    }

    public void setErsUserRoleId(int ersUserRoleId) {
        this.ersUserRoleId = ersUserRoleId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;
        UserRole userRole1 = (UserRole) o;
        return getErsUserRoleId() == userRole1.getErsUserRoleId() && Objects.equals(getUserRole(), userRole1.getUserRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getErsUserRoleId(), getUserRole());
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "ersUserRoleId=" + ersUserRoleId +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
