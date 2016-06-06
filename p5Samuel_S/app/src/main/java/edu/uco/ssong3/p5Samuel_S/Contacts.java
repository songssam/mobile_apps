package edu.uco.ssong3.p5Samuel_S;

/**
 * Created by ThinkPad on 9/22/2015.
 */
public class Contacts {
    private String fName, lName, phone, email;

    public Contacts(String fName, String lName, String phone, String email) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName= fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName= lName;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
