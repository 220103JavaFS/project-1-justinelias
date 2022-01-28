package com.revature.repos;

import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.utils.ConnectionUtil;

import java.sql.*;

public class UserDAOImpl implements UserDAO {
private UserRoleDAO userRoleDAO = new UserRoleDAOImpl();
    @Override
    public User getUserById(int id) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ers_users WHERE ers_users_id="+id+";";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            User user = new User();

            if(result.next()){
                user.setErsUsersId(result.getInt("ers_users_id"));
                user.setErsUsername(result.getString("ers_username"));
                user.setErsPassword(result.getString("ers_password"));
                user.setUserFirstName(result.getString("user_first_name"));
                user.setUserLastname(result.getString("user_last_name"));
                user.setUserEmail(result.getString("user_email"));
//                user.setUserRole(userRoleDAO.getUserRole(result.getString("ers_user_role")));
//                user.setUserRole(userRoleDAO.getRoleById(result.getInt("ers_user_role_id")));
                return user;
            }else{
                return null;
            }



        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public User getUserByUsername(String ersUsername) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ers_users WHERE ers_username= ?;";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, ersUsername);

            ResultSet result = statement.executeQuery();

            User user = new User();

            if(result.next()){
                user.setErsUsersId(result.getInt("ers_users_id"));
                user.setErsUsername(result.getString("ers_username"));
                user.setErsPassword(result.getString("ers_password"));
                user.setUserFirstName(result.getString("user_first_name"));
                user.setUserLastname(result.getString("user_last_name"));
                user.setUserEmail(result.getString("user_email"));
//                user.setUserRole(userRoleDAO.getUserRole(result.getString("ers_user_role")));
//                user.setUserRole(userRoleDAO.getRoleById(result.getInt("ers_user_role_id")));

            }
            return user;




        }catch (SQLException e){
            e.printStackTrace();
        }
        return new User();
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
