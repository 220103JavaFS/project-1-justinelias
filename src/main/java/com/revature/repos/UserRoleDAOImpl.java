package com.revature.repos;

import com.revature.models.ReimbursementType;
import com.revature.models.UserRole;
import com.revature.utils.ConnectionUtil;

import java.sql.*;

public class UserRoleDAOImpl implements UserRoleDAO{


    @Override
    public UserRole getRoleById(int id) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ers_user_roles WHERE ers_user_role_id = "+id+";";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            UserRole userRole = new UserRole();

            if(result.next()){
                userRole.setErsUserRoleId(result.getInt("ers_user_role_id"));
                userRole.setUserRole(result.getString("user_role"));
            }else{
                return null;
            }

            return userRole;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean addRole(UserRole userRole) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO ers_user_roles (user_role,ers_user_role_id) " +
                    "VALUES (?, "+userRole.getErsUserRoleId()+");";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userRole.getUserRole());
            return (statement.executeUpdate() > 0);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UserRole getUserRole(String userRole) {
        return null;
    }


}

