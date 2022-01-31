package com.karbelkardesigns.loancalculator.objects;

import java.math.BigDecimal;

public class LoanResponse {
    private double p;
    private double r;
    private int n;
    private double emi;

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getEmi() {
        return emi;
    }

    public void setEmi(double emi) {
        this.emi = emi;
    }

    public void calculateEmi(){
        double rate = r/1200;
        double freq = 12*n;
        this.emi = p*rate*((Math.pow(1+rate,freq))/(Math.pow(1+rate,freq)-1));
    }
}
