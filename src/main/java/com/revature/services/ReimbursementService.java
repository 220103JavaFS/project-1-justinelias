package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.repos.ReimbursementDAO;
import com.revature.repos.ReimbursementDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {

    private ReimbursementDAO reimbursementDAO;

    public ReimbursementService() {
        this.reimbursementDAO = new ReimbursementDAOImpl();
    }

    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public boolean addReimb(Reimbursement reimbursement) {
        return false;
    }

    public List<Reimbursement> getAllReimbs() {
        return new ArrayList<Reimbursement>();
    }

    public boolean updateReimb(Reimbursement reimbursement) {
        return false;
    }
}
