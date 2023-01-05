package com.example.myapiapplication.Customer;

import java.util.ArrayList;
import java.util.Date;


public class Datum{
        public int id;
        public int user_id;
        public Object delivery_id;
        public int work_id;
        public String details;
        public String details_address;
        public String lat;
//        @JsonProperty("long")
        public String mylong;
        public String phone;
        public int status;
        public Date created_at;
        public Date updated_at;
        public ArrayList<PhotoOrder> photo_order;
        public Work work;

        public Datum(int id, int user_id, Object delivery_id, int work_id, String details, String details_address, String lat, String mylong, String phone, int status, Date created_at, Date updated_at, ArrayList<PhotoOrder> photo_order, Work work) {
                this.id = id;
                this.user_id = user_id;
                this.delivery_id = delivery_id;
                this.work_id = work_id;
                this.details = details;
                this.details_address = details_address;
                this.lat = lat;
                this.mylong = mylong;
                this.phone = phone;
                this.status = status;
                this.created_at = created_at;
                this.updated_at = updated_at;
                this.photo_order = photo_order;
                this.work = work;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public int getUser_id() {
                return user_id;
        }

        public void setUser_id(int user_id) {
                this.user_id = user_id;
        }

        public Object getDelivery_id() {
                return delivery_id;
        }

        public void setDelivery_id(Object delivery_id) {
                this.delivery_id = delivery_id;
        }

        public int getWork_id() {
                return work_id;
        }

        public void setWork_id(int work_id) {
                this.work_id = work_id;
        }

        public String getDetails() {
                return details;
        }

        public void setDetails(String details) {
                this.details = details;
        }

        public String getDetails_address() {
                return details_address;
        }

        public void setDetails_address(String details_address) {
                this.details_address = details_address;
        }

        public String getLat() {
                return lat;
        }

        public void setLat(String lat) {
                this.lat = lat;
        }

        public String getMylong() {
                return mylong;
        }

        public void setMylong(String mylong) {
                this.mylong = mylong;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }

        public Date getCreated_at() {
                return created_at;
        }

        public void setCreated_at(Date created_at) {
                this.created_at = created_at;
        }

        public Date getUpdated_at() {
                return updated_at;
        }

        public void setUpdated_at(Date updated_at) {
                this.updated_at = updated_at;
        }

        public ArrayList<PhotoOrder> getPhoto_order() {
                return photo_order;
        }

        public void setPhoto_order(ArrayList<PhotoOrder> photo_order) {
                this.photo_order = photo_order;
        }

        public Work getWork() {
                return work;
        }

        public void setWork(Work work) {
                this.work = work;
        }
}

