package com.revature.models;

import java.util.Objects;

public class ReimbursementType {

    private int reimbTypeId;
    private String reimbType;

    public ReimbursementType() {
    }

    public ReimbursementType(int reimbTypeId, String reimbType) {
        this.reimbTypeId = reimbTypeId;
        this.reimbType = reimbType;
    }

    public int getReimbTypeId() {
        return reimbTypeId;
    }

    public void setReimbTypeId(int reimbTypeId) {
        this.reimbTypeId = reimbTypeId;
    }

    public String getReimbType() {
        return reimbType;
    }

    public void setReimbType(String reimbType) {
        this.reimbType = reimbType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReimbursementType)) return false;
        ReimbursementType that = (ReimbursementType) o;
        return getReimbTypeId() == that.getReimbTypeId() && Objects.equals(getReimbType(), that.getReimbType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReimbTypeId(), getReimbType());
    }

    @Override
    public String toString() {
        return "ReimbursementType{" +
                "reimbTypeId=" + reimbTypeId +
                ", reimbType='" + reimbType + '\'' +
                '}';
    }
}
