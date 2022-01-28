package com.revature.controllers;

import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

public class ReimbursementController implements Controller{

    ReimbursementService reimbursementService = new ReimbursementService();

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

    private Handler getAllReimbs = (ctx) ->{
        if(ctx.req.getSession(false)!=null){
            List<Reimbursement> list = reimbursementService.getAllReimbs();

            ctx.json(list);
            ctx.status(200);


        }else{
            ctx.status(401);
        }
    };

    private Handler updateReimb = (ctx) -> {
        if (ctx.req.getSession(false)!=null){
            Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
           if(reimbursementService.updateReimb(reimbursement)){
               ctx.status(202);
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
        app.get("/reimb", getAllReimbs);
        app.put("/reimb", updateReimb);
    }
}
