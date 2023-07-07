package com.boschini.demo;

public class Thing {

    int costo;
    String nameObject;

    public Thing(int costo, String nameObject) {
        this.costo = costo;
        this.nameObject = nameObject;
    }

    public String getNameObject() {
        return nameObject;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Thing{" + "costo=" + costo + ", nameObject='" + nameObject + '\'' + '}';
    }
}
