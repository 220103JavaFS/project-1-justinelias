package com.revature.repos;

import com.revature.models.ReimbursementType;

public interface ReimbursementTypeDAO {

    ReimbursementType getTypeById(int id);
    boolean addType(ReimbursementType reimbType);
}
