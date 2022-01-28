package com.revature.repos;

import com.revature.models.ReimbursementStatus;

public interface ReimbursementStatusDAO {

    public ReimbursementStatus getStatusById(int id);

    public boolean addStatus(ReimbursementStatus reimbursementStatus);
}
