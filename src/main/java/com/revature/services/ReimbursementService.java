package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.repos.ReimbursementDAO;
import com.revature.repos.ReimbursementDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {

    private ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();




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
