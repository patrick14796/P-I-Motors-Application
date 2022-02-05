package com.PI_Motors.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    public static final Model instance = new Model();

    private Model(){
        for(int i=0;i<3;i++){
            Car c = new Car ("Car " + i,"Model "+i," 55-5555-55"," 2014"," Robotic"," 1,600"," 12,000 KM"," Private"," Ashdod"," 0546178558","     25,000$",false,false,false,false);
            data.add(c);
        }
    }

    List<Car> data = new LinkedList<Car>();

    public List<Car> getAllCars(){
        return data;
    }

    public void addCar(Car car){
        data.add(car);
    }

    public Car getCarById(String carId) {
        for (Car s_:data) {
            if (s_.car_num.equals(carId)){
                return s_;
            }
        }
        return null;
    }
}