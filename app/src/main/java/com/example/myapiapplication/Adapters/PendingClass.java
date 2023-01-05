package com.example.myapiapplication.Adapters;

import java.util.Date;

public class PendingClass {

    private String OrderNum,serviceType;
    private String orderDateShow;

    public PendingClass(String orderNum, String serviceType, String orderDate) {
        OrderNum = orderNum;
        this.serviceType = serviceType;
        this.orderDateShow = orderDate;
    }

    public String getOrderNum() {
        return OrderNum;
    }

    public void setOrderNum(String orderNum) {
        OrderNum = orderNum;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getOrderDateShow() {
        return orderDateShow;
    }

    public void setOrderDateShow(String orderDateShow) {
        this.orderDateShow = orderDateShow;
    }
}
