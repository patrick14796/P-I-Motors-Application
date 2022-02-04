package com.PI_Motors.model;

public class Car {
    String type = "";
    String car_Model = "";
    String car_num = "";
    String year = "";
    String Price = "";
    String Gearbox = "";
    String Engine_capacity = "";
    String Mileage = "";
    String ownership = "";
    String Branch = "";
    String Agent_Phonenum = "";

    boolean for_Sale = false;
    boolean for_Tradein = false;
    boolean Discount = false;
    boolean Love_it = false;

    public Car(){}

    public Car(String car_type, String car_model, String car_number, String car_year, String car_gearbox, String engine_cap, String car_miles, String car_ownertype, String car_brance, String car_agentphone,String car_price,boolean car_forSale,boolean car_fortrade,boolean car_discount,boolean car_loveit) {
        this.type = car_type;
        this.car_Model = car_model;
        this.car_num = car_number;
        this.year = car_year;
        this.Price = car_price;
        this.Gearbox = car_gearbox;
        this.Engine_capacity = engine_cap;
        this.Mileage = car_miles;
        this.ownership = car_ownertype;
        this.Branch = car_brance;
        this.Agent_Phonenum = car_agentphone;
        this.for_Sale = car_forSale;
        this.for_Tradein = car_fortrade;
        this.Discount = car_discount;
        this.Love_it = car_loveit;
    }

    public void setType(String type) {
        this.type = type;
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
    public void set_Branch(String Branch) {
        this.Branch = Branch;
    }
    public void set_AgentPhonenum(String Agent_Phonenum) {
        this.Agent_Phonenum = Agent_Phonenum;
    }

    public void set_ForSale(boolean for_Sale) { this.for_Sale = for_Sale; }
    public void set_ForTradein(boolean for_Tradein) { this.for_Tradein = for_Tradein; }
    public void set_Discount(boolean Discount) { this.Discount = Discount; }
    public void set_LoveIt(boolean Love_it) { this.Love_it = Love_it; }



    public String getCar_Type() {
        return type;
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
    public String getBranch() {
        return Branch;
    }
    public String getAgent_Phonenum() {
        return Agent_Phonenum;
    }

    public boolean isFor_Sale() {
        return for_Sale;
    }
    public boolean isFor_Tradein() {
        return for_Tradein;
    }
    public boolean isDiscount() {
        return Discount;
    }
    public boolean isLove_it() {
        return Love_it;
    }



}