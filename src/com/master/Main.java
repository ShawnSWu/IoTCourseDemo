package com.master;


public class Main {
     public static void main(String[] args) {
        //example
        try {
            ApiManger apiManger = new ApiManger();
            apiManger.getSensorDataByMacAddress("aa2adde2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}