package com.PI_Motors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.PI_Motors.model.Model;
import com.PI_Motors.model.Car;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class CarDetailsFragment extends Fragment {
    Car car;
    TextView carType;
    TextView carModel;
    TextView carNumber;
    TextView carYear;
    TextView cargearbox;
    TextView carengine_capacity;
    TextView carmiles;
    TextView carownership;
    TextView caragent;
    File localfile;
    TextView carprice;
    ImageView carImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_details, container, false);

         setHasOptionsMenu(true);
         carType = view.findViewById(R.id.cartype_textview);
         carModel = view.findViewById(R.id.carmodel_textview);
         carNumber = view.findViewById(R.id.carnumber_textview);
         carYear = view.findViewById(R.id.caryear_textview);
         cargearbox = view.findViewById(R.id.cargear_textview);
         carengine_capacity = view.findViewById(R.id.carcapacity_textview);
         carmiles = view.findViewById(R.id.carmiles_textview);
         carownership = view.findViewById(R.id.carowner_textview);
         caragent = view.findViewById(R.id.caragent_textview);
         carprice = view.findViewById(R.id.carprice_textview);
         carImage = view.findViewById(R.id.imageView2);


        String carNum = CarDetailsFragmentArgs.fromBundle(getArguments()).getCarId();
        car = Model.instance.getCarById(carNum);
        if (car != null){
            updateDisplay(car);
        }

        return view;
    }


    private void updateDisplay(Car car) {
        this.car = car;
        carType.setText(car.getCar_Type());
        carModel.setText(car.getCar_Model());
        carNumber.setText(car.getCar_num());
        carYear.setText(car.getYear());
        cargearbox.setText(car.getGearbox());
        carengine_capacity.setText(car.getEngine_capacity());
        carmiles.setText(car.getMileage());
        carownership.setText(car.getOwnership());
        caragent.setText(car.getAgent_Phonenum());
        carprice.setText(car.getPrice());
        Picasso.get()
                .load(car.getCarImageUrl())
                .resize(50, 50)
                .centerCrop()
                .into(carImage);



    }
}








