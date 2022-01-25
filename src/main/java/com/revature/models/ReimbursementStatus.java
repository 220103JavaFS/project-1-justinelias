package com.revature.models;

import java.util.Objects;

public class ReimbursementStatus {

    private int reimbStatusId;
    private String reimbStatus;

    public ReimbursementStatus() {
    }

    public ReimbursementStatus(int reimbStatusId, String reimbStatus) {
        this.reimbStatusId = reimbStatusId;
        this.reimbStatus = reimbStatus;
    }

    public int getReimbStatusId() {
        return reimbStatusId;
    }

    public void setReimbStatusId(int reimbStatusId) {
        this.reimbStatusId = reimbStatusId;
    }

    public String getReimbStatus() {
        return reimbStatus;
    }

    public void setReimbStatus(String reimbStatus) {
        this.reimbStatus = reimbStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReimbursementStatus)) return false;
        ReimbursementStatus that = (ReimbursementStatus) o;
        return getReimbStatusId() == that.getReimbStatusId() && Objects.equals(getReimbStatus(), that.getReimbStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReimbStatusId(), getReimbStatus());
    }

    @Override
    public String toString() {
        return "ReimbursementStatus{" +
                "reimbStatusId=" + reimbStatusId +
                ", reimbStatus='" + reimbStatus + '\'' +
                '}';
    }
}
