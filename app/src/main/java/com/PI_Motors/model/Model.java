package com.PI_Motors.model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;


public class Model {
    private DatabaseReference mDatabase;
    public static final Model instance = new Model();

    private Model(){

//        for(int i=0;i<3;i++){
//            Car c = new Car ("Car " + i,"Model "+i," 55-5555-55"," 2014"," Robotic"," 1,600"," 12,000 KM"," Private"," Ashdod"," 0546178558","     25,000$",false,false,false,false,"");
//            data.add(c);
//        }

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

    ValueEventListener postListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            String post = dataSnapshot.getValue(String.class);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            String s = "";
        }
    };
}