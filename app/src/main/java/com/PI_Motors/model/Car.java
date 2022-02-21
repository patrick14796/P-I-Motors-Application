package com.PI_Motors.model;

public class Car {
    String car_Type = "";
    String car_Model = "";
    String car_num = "";
    String year = "";
    String Price = "";
    String Gearbox = "";
    String Engine_capacity = "";
    String Mileage = "";
    String ownership = "";
    String Agent_Phonenum = "";
    String carImageUrl = "";
    String suffixUrlIMG = "";



    public Car(){}

    public Car(String car_type, String car_model, String car_number, String car_year, String car_gearbox, String engine_cap, String car_miles, String car_ownertype, String car_agentphone,String car_price,String carImageUrl,String suffixUrlIMG) {
        this.car_Type = car_type;
        this.car_Model = car_model;
        this.car_num = car_number;
        this.year = car_year;
        this.Price = car_price;
        this.Gearbox = car_gearbox;
        this.Engine_capacity = engine_cap;
        this.Mileage = car_miles;
        this.ownership = car_ownertype;
        this.Agent_Phonenum = car_agentphone;

        this.carImageUrl = carImageUrl;
    }

    public void setType(String type) {
        this.car_Type = type;
    }
    public void set_CarModel(String car_Model) {
        this.car_Model = car_Model;
    }
    public void set_CarNum(String car_num) {
        this.car_num = car_num;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public void setPrice(String Price) {this.Price = Price; }
    public void set_Gearbox(String Gearbox) {
        this.Gearbox = Gearbox;
    }
    public void set_EngineCapacity(String Engine_capacity) {this.Engine_capacity = Engine_capacity; }
    public void set_Mileage(String Mileage) {
        this.Mileage = Mileage;
    }
    public void set_ownership(String ownership) {
        this.ownership = ownership;
    }
    public void set_AgentPhonenum(String Agent_Phonenum) {
        this.Agent_Phonenum = Agent_Phonenum;
    }
    public void set_carUrl(String carurl) {this.carImageUrl = carurl;}


    public String getCarImageSuffix() {return suffixUrlIMG;}
    public String getCarImageUrl() {
        return carImageUrl;
    }
    public String getCar_Type() {
        return car_Type;
    }
    public String getCar_Model() {
        return car_Model;
    }
    public String getCar_num() {
        return car_num;
    }
    public String getYear() {
        return year;
    }
    public String getPrice() {
        return Price;
    }
    public String getGearbox() {
        return Gearbox;
    }
    public String getEngine_capacity() {
        return Engine_capacity;
    }
    public String getMileage() {
        return Mileage;
    }
    public String getOwnership() {
        return ownership;
    }
    public String getAgent_Phonenum() {
        return Agent_Phonenum;
    }




}
