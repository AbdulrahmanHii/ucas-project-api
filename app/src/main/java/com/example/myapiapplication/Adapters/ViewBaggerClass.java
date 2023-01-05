package com.example.myapiapplication.Adapters;

import androidx.fragment.app.Fragment;

public class ViewBaggerClass {

    private String TapName;
    private Fragment fragment;

    public ViewBaggerClass(String tapName, Fragment fragment) {
        TapName = tapName;
        this.fragment = fragment;
    }

    public String getTapName() {
        return TapName;
    }

    public void setTapName(String tapName) {
        TapName = tapName;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
