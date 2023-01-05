package com.example.myapiapplication.Customer;

public class CustomerDataClass {

    private String fullNameCus,emailCus,phoneCus,passwordCus;
    private  Boolean chickStateCus;


    public CustomerDataClass(String fullNameCus, String emailCus, String phoneCus, String passwordCus) {
        this.fullNameCus = fullNameCus;
        this.emailCus = emailCus;
        this.phoneCus = phoneCus;
        this.passwordCus = passwordCus;
    }

    public String getFullNameCus() {
        return fullNameCus;
    }

    public void setFullNameCus(String fullNameCus) {
        this.fullNameCus = fullNameCus;
    }

    public String getEmailCus() {
        return emailCus;
    }

    public void setEmailCus(String emailCus) {
        this.emailCus = emailCus;
    }

    public String getPhoneCus() {
        return phoneCus;
    }

    public void setPhoneCus(String phoneCus) {
        this.phoneCus = phoneCus;
    }

    public String getPasswordCus() {
        return passwordCus;
    }

    public void setPasswordCus(String passwordCus) {
        this.passwordCus = passwordCus;
    }

    public Boolean getChickStateCus() {
        return chickStateCus;
    }

    public void setChickStateCus(Boolean chickStateCus) {
        this.chickStateCus = chickStateCus;
    }
}
