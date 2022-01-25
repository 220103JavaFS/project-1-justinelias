package com.revature.controllers;

import com.revature.models.ReimbursementStatus;
import com.revature.services.ReimbursementStatusService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementStatusController implements Controller{

    private ReimbursementStatusService reimbursementStatusService = new ReimbursementStatusService();

    private Handler getStatusById = (ctx) ->{
        if(ctx.req.getSession(false)!=null){
            String idString = ctx.pathParam("id");
            int id = Integer.parseInt(idString);
            ctx.json(reimbursementStatusService.getStatusById(id));
            ctx.status(200);
        }else {
            ctx.status(401);
        }
    };

    @Override
    public void addRoutes(Javalin app) {
        app.get("/status/{id}", getStatusById);
    }
}
