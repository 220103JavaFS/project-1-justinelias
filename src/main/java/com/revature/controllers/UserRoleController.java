package com.revature.controllers;

import com.revature.services.UserRoleService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserRoleController implements Controller{

    private UserRoleService userRoleService = new UserRoleService();

    private Handler getRoleById = (ctx) ->{
        if(ctx.req.getSession(false)!=null){
            String idString = ctx.pathParam("id");
            int id = Integer.parseInt(idString);
            ctx.json(userRoleService.getRoleById(id));
            ctx.status(200);
        }else {
            ctx.status(401);
        }
    };

    @Override
    public void addRoutes(Javalin app) {
        app.get("/role/{id}", getRoleById);
    }
}
