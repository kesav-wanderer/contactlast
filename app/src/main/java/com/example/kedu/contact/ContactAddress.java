package com.example.kedu.contact;



/**
 * Created by saike on 04-04-2018.
 */

public class ContactAddress {


    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private int contactID;

    public ContactAddress() {

        contactID = -1;

    }
    public int getContactID() {
        return contactID;
    }


    public String getStreetAddress() {return streetAddress;    }

    public void setStreetAddress(String s) {
        streetAddress = s;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String s) {
        city = s;
    }

    public String getState() {
        return state;
    }

    public void setState(String s) {
        state = s;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String s) {
        zipCode = s;
    }


}

