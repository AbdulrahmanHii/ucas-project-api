package com.example.myapiapplication.Customer;


import java.util.Date;

public class PhotoOrder{
        public int id;
        public String photo;
        public int order_id;
        public Date created_at;
        public Date updated_at;

        public PhotoOrder(int id, String photo, int order_id, Date created_at, Date updated_at) {
                this.id = id;
                this.photo = photo;
                this.order_id = order_id;
                this.created_at = created_at;
                this.updated_at = updated_at;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getPhoto() {
                return photo;
        }

        public void setPhoto(String photo) {
                this.photo = photo;
        }

        public int getOrder_id() {
                return order_id;
        }

        public void setOrder_id(int order_id) {
                this.order_id = order_id;
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
}

