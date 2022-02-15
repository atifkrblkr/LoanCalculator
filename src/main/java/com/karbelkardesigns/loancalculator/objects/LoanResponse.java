package com.karbelkardesigns.loancalculator.objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class LoanResponse {

    public final Logger log = LoggerFactory.getLogger(LoanResponse.class);
    private final double p;
    private final double r;
    private final int n;
    private final List<Amortization> amortizations;
    private double emi;
    private final double rate;
    private final double freq;
    private PayFrequency payFrequency;

    public LoanResponse(double p, double r, int n, int pf) {
        this.p = p;
        this.r = r;
        this.n = n;
        switch (pf) {
            case 0:
                this.payFrequency = PayFrequency.WEEKLY;
                break;
            case 1:
                this.payFrequency = PayFrequency.BIWEEKLY;
                break;
            case 2:
                this.payFrequency = PayFrequency.MONTHLY;
                break;
        }

        this.rate = r / (100 * payFrequency.getPf());
        this.freq = payFrequency.getPf() * n;
        this.amortizations = new LinkedList<>();
        log.info("p={}/-, r={}%, n={}, pf={}, rate={}%, freq={}", p, r, n, payFrequency, rate, freq);
    }

    public void generateAmortization() {
        calculateEmi();
        double olb = p;
        double ci = 0.0d;
        for (int i = 1; i <= freq; i++) {
            Amortization amortization = new Amortization();
            amortization.setSerial(i);
            amortization.setP(this.emi - (olb * rate));
            amortization.setI(olb * rate);
            amortization.setT(this.emi);
            olb -= amortization.getP();
            ci += amortization.getI();
            amortization.setC(ci);
            amortization.setE(olb);
            amortizations.add(amortization);
        }
    }

    public void calculateEmi() {
        this.emi = p * rate * ((Math.pow(1 + rate, freq)) / (Math.pow(1 + rate, freq) - 1));
    }

    public double getP() {
        return p;
    }

    public double getR() {
        return r;
    }

    public int getN() {
        return n;
    }

    public double getEmi() {
        return emi;
    }

    public List<Amortization> getAmortizations() {
        return amortizations;
    }

    public PayFrequency getPayFrequency() {
        return payFrequency;
    }

    public double getRate() {
        return rate;
    }

    public double getFreq() {
        return freq;
    }

    public enum PayFrequency {
        WEEKLY(52), BIWEEKLY(26), MONTHLY(12);

        int pf;

        PayFrequency(int payFrequency) {
            this.pf = payFrequency;
        }

        public int getPf() {
            return pf;
        }
    }
}
