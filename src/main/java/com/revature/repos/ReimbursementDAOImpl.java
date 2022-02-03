package com.revature.repos;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO{

    private UserDAO userDAO = new UserDAOImpl();
    private ReimbursementStatusDAO reimbursementStatusDAO = new ReimbursementStatusDAOImpl();
    private ReimbursementTypeDAO reimbursementTypeDAO = new ReimbursementTypeDAOImpl();

    @Override
    public boolean addReimb(Reimbursement reimbursement) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO ers_reimbursement (reim_amount, reimb_submitted, " +
                    "reimb_description, reimb_receipt, reimb_author, reimb_status_id, reimb_type_id) " +
                    "VALUES ("+reimbursement.getReimbAmount()+", ?, ?, ?, "+reimbursement.getReimbAuthor().getErsUsersId()+
                    ", 1, "+reimbursement.getReimbType().getReimbTypeId()+");";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setTimestamp(1, reimbursement.getReimbSubmitted());
            statement.setString(2, reimbursement.getReimbDescription());
            statement.setBytes(3, reimbursement.getReimbReceipt());


            return (statement.executeUpdate() > 0);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Reimbursement> getAllReimbs() {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ers_reimbursement ORDER BY reimb_submitted DESC;";

            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery(sql);

            List<Reimbursement> list = new ArrayList<>();

            while(result.next()){
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setReimbId(result.getInt("reimb_id"));
                reimbursement.setReimbAmount(result.getDouble("reim_amount"));
                reimbursement.setReimbSubmitted(result.getTimestamp("reimb_submitted"));
                reimbursement.setReimbResolved(result.getTimestamp("reimb_resolved"));
                byte[] b = result.getBytes("reimb_receipt");
                if(b!=null){
                    reimbursement.setReimbReceipt(b);
                }
                reimbursement.setReimbDescription(result.getString("reimb_description"));
                reimbursement.setReimbAuthor(userDAO.getUserById(result.getInt("reimb_author")));
                reimbursement.setReimbResolver(userDAO.getUserById(result.getInt("reimb_resolver")));
                reimbursement.setReimbStatus(reimbursementStatusDAO.getStatusById(result.getInt("reimb_status_id")));
                reimbursement.setReimbType(reimbursementTypeDAO.getTypeById(result.getInt("reimb_type_id")));

                list.add(reimbursement);
            }

            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean updateReimb(Reimbursement reimbursement) {

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "UPDATE ers_reimbursement SET reimb_resolved = ?, reimb_resolver = "
                    +reimbursement.getReimbResolver().getErsUsersId()+", reimb_status_id = "
                    +reimbursement.getReimbStatus().getReimbStatusId()
                    +" WHERE reimb_id = "+reimbursement.getReimbId()+";";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setTimestamp(1, reimbursement.getReimbResolved());

            return (statement.executeUpdate() > 0);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public byte[] getReimbReceipt(int reimbId) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT reimb_receipt FROM ers_reimbursement WHERE reimb_id = "+reimbId+";";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if(result.next()){
                return result.getBytes("reimb_receipt");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
