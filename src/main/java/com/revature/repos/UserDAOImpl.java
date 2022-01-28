package com.revature.repos;

import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.utils.ConnectionUtil;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    @Override
    public User getUserById(int id) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ers_users WHERE ers_users_id = "+id+";";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            User user = new User();

            if(result.next()){
                user.setErsUsersId(result.getInt("ers_users_id"));
                user.setErsUsername(result.getString("ers_username"));
            }

            return user;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public User getUserByUsername(String ersUsername) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ers_users WHERE ers_username= "+", ?, "+";";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            User user = new User();

            if(result.next()){
                user.setErsUsersId(result.getInt("ers_users_id"));
                user.setErsUsername(result.getString("ers_username"));
            }

            return user;

        }catch (SQLException e){
            e.printStackTrace();
        }
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
