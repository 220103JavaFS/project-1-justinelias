package com.revature.controllers;

import com.revature.models.User;
import io.javalin.Javalin;
import io.javalin.http.Handler;


public class UserController implements Controller{

    private UserService userService = new UserService;

    private Handler getUserById = (ctx) ->{
        if(ctx.req.getSession(false)!=null){
            String idString = ctx.pathParam("id");
            int id = Integer.parseInt(idString);
            User user = userService.getUserById(id);
            ctx.json(user);
            ctx.status(200);
        }else{
            ctx.status(401);
        }
    };



    @Override
    public void addRoutes(Javalin app) {
        app.get("/user/{id}", getUserById);
    }
}
