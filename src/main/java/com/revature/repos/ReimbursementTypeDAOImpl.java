package com.revature.repos;

import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.utils.ConnectionUtil;

import java.sql.*;

public class ReimbursementTypeDAOImpl implements ReimbursementTypeDAO{


    @Override
    public ReimbursementType getTypeById(int id) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ers_reimbursement_type WHERE reimb_type_id = "+id+";";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            ReimbursementType type = new ReimbursementType();

            if(result.next()){
                type.setReimbTypeId(result.getInt("reimb_type_id"));
                type.setReimbType(result.getString("reimb_type"));
                return type;
            }else{
                return null;
            }



        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean addType(ReimbursementType reimbursementType) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO ers_reimbursement_type (reimb_type_id, reimb_type) " +
                    "VALUES ("+reimbursementType.getReimbTypeId()+", ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, reimbursementType.getReimbType());
            return (statement.executeUpdate() > 0);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
