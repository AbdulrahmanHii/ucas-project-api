package com.example.myapiapplication.Adapters;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class SelectServiceClass implements Serializable {
    private String nameService;
    private int idService;
    private Drawable iconService ;

    public SelectServiceClass(String nameService, int idService, Drawable iconService) {
        this.nameService = nameService;
        this.idService = idService;
        this.iconService = iconService;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public Drawable getIconService() {
        return iconService;
    }

    public void setIconService(Drawable iconService) {
        this.iconService = iconService;
    }
}
