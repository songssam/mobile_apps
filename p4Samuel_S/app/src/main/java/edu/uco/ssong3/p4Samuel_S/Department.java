package edu.uco.ssong3.p4Samuel_S;

/**
 * Created by ThinkPad on 9/18/2015.
 */
public class Department {
    private String name;
    private String number;
    private String web;

    public Department(String name, String number, String web) {
        this.name = name;
        this.number = number;
        this.web = web;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

}
