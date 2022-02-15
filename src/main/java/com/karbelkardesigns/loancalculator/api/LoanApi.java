package com.karbelkardesigns.loancalculator.api;

import com.karbelkardesigns.loancalculator.objects.Amortization;
import com.karbelkardesigns.loancalculator.objects.LoanResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.text.DecimalFormat;
import java.util.List;

@RestController
@RequestMapping(path = "/karbelkar-designs")
public class LoanApi {

    public final Logger log = LoggerFactory.getLogger(LoanApi.class);

    @GetMapping(path = "/get-emi")
    public String getEmi(
            @PathParam("p") double p,
            @PathParam("r") double r,
            @PathParam("n") int n,
            @PathParam("pf") int pf) {
        LoanResponse lr = new LoanResponse(p, r, n, pf);
        lr.calculateEmi();
        String emi = new DecimalFormat("#,###.##").format(lr.getEmi());
        log.info("emi={}", emi);
        return emi;
    }

    @GetMapping(path = "/get-amortization")
    public List<Amortization> getAmortization(
            @PathParam("p") double p,
            @PathParam("r") double r,
            @PathParam("n") int n,
            @PathParam("pf") int pf) {
        LoanResponse lr = new LoanResponse(p, r, n, pf);
        lr.generateAmortization();
        return lr.getAmortizations();
    }
}
