package com.revature.repos;

import com.revature.models.ReimbursementStatus;

public interface ReimbursementStatusDAO {

    ReimbursementStatus getStatusById(int id);
}
