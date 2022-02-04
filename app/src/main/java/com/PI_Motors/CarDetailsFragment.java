package com.PI_Motors;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.PI_Motors.model.Model;
import com.PI_Motors.model.Car;

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
    TextView carbarnch;
    TextView caragent;
    CheckBox carforsale;
    CheckBox carfortrade;
    CheckBox cardiscount;
    CheckBox carloveit;
    TextView carprice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_details, container, false);


         carType = view.findViewById(R.id.cartype_textview);
         carModel = view.findViewById(R.id.carmodel_textview);
         carNumber = view.findViewById(R.id.carnumber_textview);
         carYear = view.findViewById(R.id.caryear_textview);
         cargearbox = view.findViewById(R.id.cargear_textview);
         carengine_capacity = view.findViewById(R.id.carcapacity_textview);
         carmiles = view.findViewById(R.id.carmiles_textview);
         carownership = view.findViewById(R.id.carowner_textview);
         carbarnch = view.findViewById(R.id.carbranch_textview);
         caragent = view.findViewById(R.id.caragent_textview);
         carforsale = view.findViewById(R.id.carforsale_checkbox);
         carfortrade = view.findViewById(R.id.carfortrade_checkbox);
         cardiscount = view.findViewById(R.id.cardiscount_checkbox);
         carloveit = view.findViewById(R.id.carloveit_checkbox);
         carprice = view.findViewById(R.id.carprice_textview);

        String carNum = CarDetailsFragmentArgs.fromBundle(getArguments()).getCarId();
        int position = CarDetailsFragmentArgs.fromBundle(getArguments()).getCarPosition();
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
        carbarnch.setText(car.getBranch());
        caragent.setText(car.getAgent_Phonenum());
        carforsale.setChecked(false);
        carfortrade.setChecked(false);
        cardiscount.setChecked(false);
        carloveit.setChecked(false);
        carprice.setText(car.getPrice());
    }
}