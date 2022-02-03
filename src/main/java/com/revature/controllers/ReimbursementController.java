package com.revature.controllers;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

public class ReimbursementController implements Controller{

    ReimbursementService reimbursementService = new ReimbursementService();

    private Handler newReimb = (ctx) ->{
        if(ctx.req.getSession(false)!=null){
            User user = (User) ctx.req.getSession().getAttribute("userInfo");
            Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
            reimbursement.setReimbAuthor(user);
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
            User user = (User) ctx.req.getSession().getAttribute("userInfo");
            reimbursement.setReimbResolver(user);

            if(user.getUserRole().getErsUserRoleId() < 2){
                ctx.status(401);
            }else if(reimbursementService.updateReimb(reimbursement)){
               ctx.json(user);
               ctx.status(202);
            }else{
               ctx.status(400);
            }
        }else{
            ctx.status(401);
        }
    };

    private Handler getReimbReceipt = (ctx) ->{
        if (ctx.req.getSession(false)!=null){
            int reimbId = Integer.parseInt(ctx.pathParam("id"));
            byte[] receipt = reimbursementService.getReimbReceipt(reimbId);
            if(receipt!=null){
                Reimbursement reimb = new Reimbursement();
                reimb.setReimbReceipt(receipt);
                ctx.json(reimb);
                ctx.status(200);
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
        app.post("/reimb/receipt/{id}", getReimbReceipt);
    }
}
