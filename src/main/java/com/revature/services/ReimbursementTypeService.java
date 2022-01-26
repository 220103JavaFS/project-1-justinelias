package com.revature.services;

import com.revature.models.ReimbursementType;
import com.revature.repos.ReimbursementStatusDAO;
import com.revature.repos.ReimbursementTypeDAO;
import com.revature.repos.ReimbursementTypeDAOImpl;

public class ReimbursementTypeService {

    private ReimbursementTypeDAO reimbursementTypeDAO;

    public ReimbursementTypeService(ReimbursementTypeDAO reimbursementTypeDAO) {
        this.reimbursementTypeDAO = reimbursementTypeDAO;
    }

    public ReimbursementTypeService() {
        this.reimbursementTypeDAO = new ReimbursementTypeDAOImpl();
    }


    public ReimbursementType getTypeById(int id) {
        return null;
    }
    public boolean addType(ReimbursementType reimbType){
        return false;
    }
}
