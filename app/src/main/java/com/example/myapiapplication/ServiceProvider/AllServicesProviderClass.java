package com.example.myapiapplication.ServiceProvider;

import android.net.Uri;

public class AllServicesProviderClass {
   // photo,date,serviceType,orderId,nameCustomer

   private  String photo,date,serviceType,orderId,nameCustomer;

   public AllServicesProviderClass(String photo, String date, String serviceType, String orderId, String nameCustomer) {
      this.photo = photo;
      this.date = date;
      this.serviceType = serviceType;
      this.orderId = orderId;
      this.nameCustomer = nameCustomer;
   }

   public String  getPhoto() {
      return photo;
   }

   public void setPhoto(String photo) {
      this.photo = photo;
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public String getServiceType() {
      return serviceType;
   }

   public void setServiceType(String serviceType) {
      this.serviceType = serviceType;
   }

   public String getOrderId() {
      return orderId;
   }

   public void setOrderId(String orderId) {
      this.orderId = orderId;
   }

   public String getNameCustomer() {
      return nameCustomer;
   }

   public void setNameCustomer(String nameCustomer) {
      this.nameCustomer = nameCustomer;
   }
}
