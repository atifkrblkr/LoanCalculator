package com.karbelkardesigns.loancalculator.api;

import com.karbelkardesigns.loancalculator.objects.LoanResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/karbelkar-designs")
public class LoanApi {

    public final Logger log = LoggerFactory.getLogger(LoanApi.class);

    @GetMapping(path = "/get-emi")
    public double getEmi(
            @PathParam("p") double p,
            @PathParam("r") double r,
            @PathParam("n") int n){
        log.info("p= Rs.{}/-, r= {}%, n= {} years",p,r,n);
        LoanResponse lr = new LoanResponse();
        lr.setP(p);
        lr.setR(r);
        lr.setN(n);
        lr.calculateEmi();
        log.info("emi= Rs. {}/-",lr.getEmi());
        return lr.getEmi();
    }
}
