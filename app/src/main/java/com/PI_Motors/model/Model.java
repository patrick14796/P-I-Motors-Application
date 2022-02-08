package com.PI_Motors.model;



import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;


public class Model {
    private DatabaseReference mDatabase;
    public static final Model instance = new Model();

    private Model(){

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