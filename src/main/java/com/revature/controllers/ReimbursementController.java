package com.revature.controllers;

import com.revature.models.Reimbursement;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementController implements Controller{

    ReimbursementService reimbursementService = new ReimbursementService;

    private Handler newReimb = (ctx) ->{
        if(ctx.req.getSession(false)!=null){
            Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
            if(reimbursementService.addReimb(reimbursement)){
                ctx.status(201);
            }else{
                ctx.status(400);
            }
        }else{
            ctx.status(401);
        }
    };



    @Override
    public void addRoutes(Javalin app) {
        app.post("/reimb", newReimb);
    }
}
