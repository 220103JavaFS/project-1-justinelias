package com.revature.repos;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User getUserByUsername(String ersUsername) {
        return null;
    }

    @Override
    public boolean addUser(User user) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO ers_users (ers_username, ers_password, user_first_name " +
                    "user_last_name, user_email, user_role_id) " +
                    "VALUES ("+user.getErsUsername()+", ?, ?, "+user.getErsPassword()+ ", ?,"
                   +user.getUserFirstName()+", ?, "+user.getUserLastname()+", ?, "+user.getUserEmail()+", ?, "+
                   ", 1, " +user.getUserRole().getErsUserRoleId()+", ?, ;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getErsUsername());

            return (statement.executeUpdate() > 0);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


}
