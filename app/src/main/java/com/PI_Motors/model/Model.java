package com.PI_Motors.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    public static final Model instance = new Model();

    private Model(){
        for(int i=0;i<3;i++){
            Car c = new Car ("Car " + i,"Model "+i,"Car-Number "+i,"Year: 2014","Gearbox: Robotic","Engine-Capacity: 1,600","Miles: 12,000","Owner-Type: Private","Branch: Ashdod","Agent-Phone: 0546178558","     25,000$",false,false,false,false);
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