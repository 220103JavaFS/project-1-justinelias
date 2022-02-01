package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.repos.ReimbursementDAO;
import com.revature.repos.ReimbursementDAOImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        //generate timestamp for submission time
        Timestamp submissionTime = Timestamp.valueOf(LocalDateTime.now());
        reimbursement.setReimbSubmitted(submissionTime);

        //verify that amount, author  id, and type id are all greater than zero
        if(reimbursement.getReimbAmount() > 0 && reimbursement.getReimbAuthor().getErsUsersId() > 0
            && reimbursement.getReimbType().getReimbTypeId() > 0){

            return reimbursementDAO.addReimb(reimbursement);
        }

        return false;
    }

    public List<Reimbursement> getAllReimbs() {
        return reimbursementDAO.getAllReimbs();
    }

    public boolean updateReimb(Reimbursement reimbursement) {
        //check if resolver is not the same person as the author
        //and if resolved timestamp comes after submitted timestamp
        if(reimbursement.getReimbResolver().getErsUsersId() != reimbursement.getReimbAuthor().getErsUsersId() &&
            reimbursement.getReimbResolved().compareTo(reimbursement.getReimbSubmitted()) > 0){
            return reimbursementDAO.updateReimb(reimbursement);
        }else{
            return false;
        }
    }
}
