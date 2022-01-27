package com.revature.repos;

import com.revature.models.ReimbursementStatus;
import com.revature.utils.ConnectionUtil;

import java.sql.*;

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
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO ers_reimbursement_status (reimb_status_id, reimb_status) " +
                    "VALUES ("+reimbursementStatus.getReimbStatusId()+", ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, reimbursementStatus.getReimbStatus());

            return (statement.executeUpdate() > 0);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
