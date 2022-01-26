package com.revature.services;

import com.revature.models.ReimbursementStatus;
import com.revature.repos.ReimbursementStatusDAO;
import com.revature.repos.ReimbursementStatusDAOImpl;

public class ReimbursementStatusService {

   private ReimbursementStatusDAO reimbursementStatusDAO;

    public ReimbursementStatusService(ReimbursementStatusDAO reimbursementStatusDAO) {
        this.reimbursementStatusDAO = reimbursementStatusDAO;
    }

    public ReimbursementStatusService() {
        this.reimbursementStatusDAO = new ReimbursementStatusDAOImpl()
    }

    public ReimbursementStatus getStatusById(int id) {
        return null;
    }
}
