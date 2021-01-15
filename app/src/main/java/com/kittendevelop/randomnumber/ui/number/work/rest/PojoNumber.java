package com.kittendevelop.randomnumber.ui.number.work.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PojoNumber {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("data")
    @Expose
    private List<String> data = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
