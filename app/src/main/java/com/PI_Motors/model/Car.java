package com.PI_Motors.model;

public class Car {
    String car_Type = "";
    String car_Model = "";
    String car_num = "";
    String year = "";
    String Price = "";
    String gearbox = "";
    String engine_capacity = "";
    String mileage = "";
    String ownership = "";
    String agent_Phonenum = "";
    String carImageUrl = "";
    String suffixUrlIMG = "";



    public Car(){}

    public Car(String car_type, String car_model, String car_number, String car_year, String car_gearbox, String engine_cap, String car_miles, String car_ownertype, String car_agentphone,String car_price,String carImageUrl,String suffixUrlIMG) {
        this.car_Type = car_type;
        this.car_Model = car_model;
        this.car_num = car_number;
        this.year = car_year;
        this.Price = car_price;
        this.gearbox = car_gearbox;
        this.engine_capacity = engine_cap;
        this.mileage = car_miles;
        this.ownership = car_ownertype;
        this.agent_Phonenum = car_agentphone;

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
        this.gearbox = Gearbox;
    }
    public void set_EngineCapacity(String Engine_capacity) {this.engine_capacity = Engine_capacity; }
    public void set_Mileage(String Mileage) {
        this.mileage = Mileage;
    }
    public void set_ownership(String ownership) {
        this.ownership = ownership;
    }
    public void set_AgentPhonenum(String Agent_Phonenum) {
        this.agent_Phonenum = Agent_Phonenum;
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
        return gearbox;
    }
    public String getEngine_capacity() {
        return engine_capacity;
    }
    public String getMileage() {
        return mileage;
    }
    public String getOwnership() {
        return ownership;
    }
    public String getAgent_Phonenum() {
        return agent_Phonenum;
    }




}
