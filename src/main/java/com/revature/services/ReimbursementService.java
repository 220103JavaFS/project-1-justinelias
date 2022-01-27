package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.repos.ReimbursementDAO;
import com.revature.repos.ReimbursementDAOImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        Timestamp submissionTime = Timestamp.valueOf(LocalDateTime.now());

        if(reimbursement.getReimbAmount() > 0){

        }

        return false;
    }

    public List<Reimbursement> getAllReimbs() {
        return reimbursementDAO.getAllReimbs();
    }

    public boolean updateReimb(Reimbursement reimbursement) {
        //check if resolver is not the same person as the author
        //and if resolved timestamp comes after submitted timestamp
        if(!reimbursement.getReimbResolver().equals(reimbursement.getReimbAuthor()) &&
            reimbursement.getReimbResolved().compareTo(reimbursement.getReimbSubmitted()) > 0){

            return reimbursementDAO.updateReimb(reimbursement);
        }else{
            return false;
        }
    }
}
