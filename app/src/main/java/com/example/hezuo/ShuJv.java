package com.example.hezuo;

import org.litepal.crud.DataSupport;

public class ShuJv extends DataSupport {
    private int id;
    private String carId;
    private String money;
    private String time;


    ShuJv(){

    }
    ShuJv(String carId,String money,String time){
        this.carId = carId;
        this.money = money;
        this.time = time;

    }
    ShuJv(int id,String carId,String money,String time){
        this.id = id;
        this.carId = carId;
        this.money = money;
        this.time = time;

    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
