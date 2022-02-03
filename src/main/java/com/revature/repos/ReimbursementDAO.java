package com.revature.repos;

import com.revature.models.Reimbursement;

import java.util.List;

public interface ReimbursementDAO {
    public boolean addReimb(Reimbursement reimbursement);
    public List<Reimbursement> getAllReimbs();
    public boolean updateReimb(Reimbursement reimbursement);
    public byte[] getReimbReceipt(int reimbId);

}
