package com.revature.controllers;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementTypeController implements Controller{

    private ReimbursementTypeService reimbursementTypeService = new ReimbursementTypeService;

    private Handler getTypeByID = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            String idString = ctx.pathParam("id");
            int id = Integer.parseInt(idString);
            ctx.json(reimbursementTypeService.getTypeById(id));
            ctx.status(200);
        }else {
            ctx.status(401);
        }
    };



    @Override
    public void addRoutes(Javalin app) {
        app.get("/type/{id}", getTypeByID);
    }
}
