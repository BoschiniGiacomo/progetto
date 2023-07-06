package com.boschini.demo;

public class Thing {

    double costo;

    int rischio;

    public double getCosto() {
        return costo;
    }

    public Thing(double costo, int rischio) {
        this.costo = costo;
        this.rischio = rischio;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getRischio() {
        return rischio;
    }

    public void setRischio(int rischio) {
        this.rischio = rischio;
    }

    @Override
    public String toString() {
        return "thing{" + "costo=" + costo + ", rischio=" + rischio + '}';
    }
}
