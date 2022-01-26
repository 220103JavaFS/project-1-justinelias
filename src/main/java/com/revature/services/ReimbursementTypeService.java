package com.revature.services;

import com.revature.models.ReimbursementType;
import com.revature.repos.ReimbursementTypeDAO;
import com.revature.repos.ReimbursementTypeDAOImpl;

public class ReimbursementTypeService {
    private ReimbursementTypeDAO reimbursementTypeDAO;

    public ReimbursementTypeService() {
        this.reimbursementTypeDAO = new ReimbursementTypeDAOImpl();
    }

    public ReimbursementTypeService(ReimbursementTypeDAO reimbursementTypeDAO) {
        this.reimbursementTypeDAO = reimbursementTypeDAO;
    }
    public ReimbursementType getTypeById(int id) {
        return null;
    }
    public boolean addType(ReimbursementType reimbType){
        return false;
    }
}
