package com.revature.repos;

import com.revature.models.ReimbursementType;
import com.revature.models.UserRole;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            }

            return userRole;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean addRole(UserRole userRole) {
        return false;
    }
}
