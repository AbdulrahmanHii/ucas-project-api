package com.example.myapiapplication.ServiceProvider;

public class ServiceProviderDataClass {
    private String fullNameS,emailS,phoneS,passwordS;
    private  Boolean chickState;
    private  String  jopSelectedS;

    public ServiceProviderDataClass(String fullNameS, String emailS, String phoneS, String passwordS, String jopSelectedS) {
        this.fullNameS = fullNameS;
        this.emailS = emailS;
        this.phoneS = phoneS;
        this.passwordS = passwordS;
        this.jopSelectedS = jopSelectedS;
    }

    public String getFullNameS() {
        return fullNameS;
    }

    public void setFullNameS(String fullNameS) {
        this.fullNameS = fullNameS;
    }

    public String getEmailS() {
        return emailS;
    }

    public void setEmailS(String emailS) {
        this.emailS = emailS;
    }

    public String getPhoneS() {
        return phoneS;
    }

    public void setPhoneS(String phoneS) {
        this.phoneS = phoneS;
    }

    public String getPasswordS() {
        return passwordS;
    }

    public void setPasswordS(String passwordS) {
        this.passwordS = passwordS;
    }

    public String getJopSelectedS() {
        return jopSelectedS;
    }

    public void setJopSelectedS(String jopSelectedS) {
        this.jopSelectedS = jopSelectedS;
    }

    public Boolean getChickState() {
        return chickState;
    }

    public void setChickState(Boolean chickState) {
        this.chickState = chickState;
    }
}
