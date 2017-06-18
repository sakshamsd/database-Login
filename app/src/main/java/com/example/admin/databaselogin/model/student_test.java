package com.example.admin.databaselogin.model;

import java.util.List;

/**
 * Created by Admin on 6/14/2017.
 */

public class student_test {


    String id;
    String name;
    String address;
    String email;
    String added_by;
    //public boolean shouldSticky;

    public String getAdded_by() {
        return added_by;
    }

    public void setAdded_by(String added_by) {
        this.added_by = added_by;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public student_test(String name, String address){
        this.name = name;
        this.address = address;
    }

    public student_test(String id, String name, String address, String added_by){
        this.id = id;
        this.name = name;
        this.address = address;
        this.added_by = added_by;

    }
    public student_test() {


    }

    /**
     * status : 0
     * data : [{"id":"123","name":"suraj bhandarui","address":"nardevi","added_by":"lkasddf;asdfj"},
     * {"id":"1","name":"suraj","address":"jkaldf","added_by":"kljsdfjka"},
     * {"id":"1221","name":"suraj","address":"abc","added_by":"kljsdfjka"},
     * {"id":"6","name":"jahsjeks","address":"nskwood","added_by":"rainamhr627@gmail.com"},
     * {"id":"7","name":"Raina","address":"Kirtipur","added_by":"rainamhr627@gmail.com"},
     * "id":"8","name":"parth ","address":"chovar","added_by":"rainamhr627@gmail.com"},
     * "id":"10","name":"Ronaldo","address":"Portugal","added_by":"rainamhr627@gmail.com"},
     * "id":"2","name":"Novel","address":"Panga","added_by":"rainamhr627@gmail.com"},
     * {"id":"3","name":"Marcelo","address":"Brazil","added_by":"rainamhr627@gmail.com"},
     * {"id":"4","name":"Bale","address":"Wales","added_by":"rainamhr627@gmail.com"},
     * {"id":"5","name":"Benzema","address":"France","added_by":"rainamhr627@gmail.com"},
     * {"id":"9","name":"Isco","address":"Spain","added_by":"rainamhr627@gmail.com"}]
     */

    /*private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        *//**
         * id : 123
         * name : suraj bhandarui
         * address : nardevi
         * added_by : lkasddf;asdfj
         *//*

        private String id;


        private String name;
        private String address;
        private String added_by;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAdded_by() {
            return added_by;
        }

        public void setAdded_by(String added_by) {
            this.added_by = added_by;


        }
        public DataBean(String id, String name, String address, String added_by) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.added_by = added_by;
        }

    }*/




}
