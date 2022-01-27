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
        if(id > 0){
            return reimbursementTypeDAO.getTypeById(id);
        }else{
            return null;
        }

    }

    public boolean addType(ReimbursementType reimbursementType){
        if(reimbursementType.getReimbTypeId() > 0){
            return reimbursementTypeDAO.addType(reimbursementType);
        }else{
            return false;
        }
    }
}
