package com.example.admin.databaselogin.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 6/16/2017.
 */

public class StudentResult {

    public String status;

    @SerializedName("data")
    public List<student_test> data;

    public StudentResult(String status , List<student_test> data) {
        this.status = status;
        this.data = data;
    }
}
