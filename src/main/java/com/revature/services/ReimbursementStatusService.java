package com.revature.services;

import com.revature.models.ReimbursementStatus;
import com.revature.repos.ReimbursementStatusDAO;
import com.revature.repos.ReimbursementStatusDAOImpl;

public class ReimbursementStatusService {

    private ReimbursementStatusDAO reimbursementStatusDAO;

    public ReimbursementStatusService() {
        this.reimbursementStatusDAO = new ReimbursementStatusDAOImpl();
    }

    public ReimbursementStatusService(ReimbursementStatusDAO reimbursementStatusDAO) {
        this.reimbursementStatusDAO = reimbursementStatusDAO;
    }

    public ReimbursementStatus getStatusById(int id) {
        if(id > 0){
            return reimbursementStatusDAO.getStatusById(id);
        }else{
            return null;
        }

    }
    public boolean addStatus(ReimbursementStatus reimbursementStatus){
        if(reimbursementStatus.getReimbStatusId() > 0){
            return reimbursementStatusDAO.addStatus(reimbursementStatus);
        }else{
            return false;
        }

    }
}
