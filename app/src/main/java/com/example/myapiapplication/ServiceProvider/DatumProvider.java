package com.example.myapiapplication.ServiceProvider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DatumProvider {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("details_address")
    @Expose
    private String detailsAddress;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;
    @SerializedName("work_id")
    @Expose
    private Integer workId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("work")
    @Expose
    private WorkProvider workProvider;
    @SerializedName("photo_order_home")
    @Expose
    private PhotoOrderHomeProvider photoOrderHome;
    @SerializedName("user")
    @Expose
    private UserProvider userProvider;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public WorkProvider getWorkProvider() {
        return workProvider;
    }

    public void setWorkProvider(WorkProvider workProvider) {
        this.workProvider = this.workProvider;
    }

    public PhotoOrderHomeProvider getPhotoOrderHome() {
        return photoOrderHome;
    }

    public void setPhotoOrderHome(PhotoOrderHomeProvider photoOrderHomeProvider) {
        this.photoOrderHome = photoOrderHomeProvider;
    }

    public UserProvider getUserProvider() {
        return userProvider;
    }

    public void setUserProvider(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

}