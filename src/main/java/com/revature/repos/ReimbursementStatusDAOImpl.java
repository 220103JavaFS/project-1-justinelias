package com.revature.repos;

import com.revature.models.ReimbursementStatus;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReimbursementStatusDAOImpl implements ReimbursementStatusDAO{
    @Override
    public ReimbursementStatus getStatusById(int id) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ers_reimbursement_status WHERE reimb_status_id = "+id+";";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            ReimbursementStatus status = new ReimbursementStatus();

            if(result.next()){
                status.setReimbStatusId(result.getInt("reimb_status_id"));
                status.setReimbStatus(result.getString("reimb_status"));
            }

            return status;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addStatus(ReimbursementStatus reimbursementStatus) {
        return false;
    }
}
