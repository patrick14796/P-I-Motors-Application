package com.PI_Motors;

import com.PI_Motors.model.Car;

public class Upload {
    private Car car;
    private String mImageUrl;
    public Upload() {
        //empty constructor needed
    }
    public Upload(Car car, String imageUrl) {

        car = car;
        mImageUrl = imageUrl;
    }
    public String getName() {
        return car.getCar_Model();
    }
    public void setName(String name) {
        car.set_CarModel(name);
    }
    public String getImageUrl() {
        return mImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}