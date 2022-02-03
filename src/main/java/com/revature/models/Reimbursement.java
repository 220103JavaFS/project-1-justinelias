package com.revature.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Reimbursement {

    private int reimbId;
    private double reimbAmount;
    private Timestamp reimbSubmitted;
    private Timestamp reimbResolved;
    private String reimbDescription;
    private byte[] reimbReceipt;
    private User reimbAuthor;
    private User reimbResolver;
    private ReimbursementStatus reimbStatus;
    private ReimbursementType reimbType;

    public Reimbursement() {
    }

    public Reimbursement(int reimbId, double reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved, String reimbDescription, byte[] reimbReceipt, User reimbAuthor, User reimbResolver, ReimbursementStatus reimbStatus, ReimbursementType reimbType) {
        this.reimbId = reimbId;
        this.reimbAmount = reimbAmount;
        this.reimbSubmitted = reimbSubmitted;
        this.reimbResolved = reimbResolved;
        this.reimbDescription = reimbDescription;
        this.reimbReceipt = reimbReceipt;
        this.reimbAuthor = reimbAuthor;
        this.reimbResolver = reimbResolver;
        this.reimbStatus = reimbStatus;
        this.reimbType = reimbType;
    }

    public Reimbursement(int reimbId, double reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved, String business_dinner, User reimbAuthor, User reimbResolver, String resolved, String food) {
    }

    public int getReimbId() {
        return reimbId;
    }

    public void setReimbId(int reimbId) {
        this.reimbId = reimbId;
    }

    public double getReimbAmount() {
        return reimbAmount;
    }

    public void setReimbAmount(double reimbAmount) {
        this.reimbAmount = reimbAmount;
    }

    public Timestamp getReimbSubmitted() {
        return reimbSubmitted;
    }

    public void setReimbSubmitted(Timestamp reimbSubmitted) {
        this.reimbSubmitted = reimbSubmitted;
    }

    public Timestamp getReimbResolved() {
        return reimbResolved;
    }

    public void setReimbResolved(Timestamp reimbResolved) {
        this.reimbResolved = reimbResolved;
    }

    public String getReimbDescription() {
        return reimbDescription;
    }

    public void setReimbDescription(String reimbDescription) {
        this.reimbDescription = reimbDescription;
    }

    public User getReimbAuthor() {
        return reimbAuthor;
    }

    public void setReimbAuthor(User reimbAuthor) {
        this.reimbAuthor = reimbAuthor;
    }

    public User getReimbResolver() {
        return reimbResolver;
    }

    public void setReimbResolver(User reimbResolver) {
        this.reimbResolver = reimbResolver;
    }

    public ReimbursementStatus getReimbStatus() {
        return reimbStatus;
    }

    public void setReimbStatus(ReimbursementStatus reimbStatus) {
        this.reimbStatus = reimbStatus;
    }

    public ReimbursementType getReimbType() {
        return reimbType;
    }

    public void setReimbType(ReimbursementType reimbType) {
        this.reimbType = reimbType;
    }

    public byte[] getReimbReceipt() {
        return reimbReceipt;
    }

    public void setReimbReceipt(byte[] reimbReceipt) {
        this.reimbReceipt = reimbReceipt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reimbursement)) return false;
        Reimbursement that = (Reimbursement) o;
        return getReimbId() == that.getReimbId() && Double.compare(that.getReimbAmount(), getReimbAmount()) == 0 && Objects.equals(getReimbSubmitted(), that.getReimbSubmitted()) && Objects.equals(getReimbResolved(), that.getReimbResolved()) && Objects.equals(getReimbDescription(), that.getReimbDescription()) && Objects.equals(getReimbAuthor(), that.getReimbAuthor()) && Objects.equals(getReimbResolver(), that.getReimbResolver()) && Objects.equals(getReimbStatus(), that.getReimbStatus()) && Objects.equals(getReimbType(), that.getReimbType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReimbId(), getReimbAmount(), getReimbSubmitted(), getReimbResolved(), getReimbDescription(), getReimbAuthor(), getReimbResolver(), getReimbStatus(), getReimbType());
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbId=" + reimbId +
                ", reimbAmount=" + reimbAmount +
                ", reimbSubmitted=" + reimbSubmitted +
                ", reimbResolved=" + reimbResolved +
                ", reimbDescription='" + reimbDescription + '\'' +
                ", reimbAuthor=" + reimbAuthor +
                ", reimbResolver=" + reimbResolver +
                ", reimbStatus=" + reimbStatus +
                ", reimbType=" + reimbType +
                '}';
    }
}
