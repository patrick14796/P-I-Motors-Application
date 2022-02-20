package com.PI_Motors;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ProgressBar;
import android.widget.TextView;


import com.PI_Motors.model.Model;
import com.PI_Motors.model.Car;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.LinkedList;
import java.util.List;


public class EditCarFragment extends Fragment {
    private List<Car> data = new LinkedList<Car>();
    Car car;
    TextView car_Type;
    TextView car_Model;
    TextView car_num;
    TextView year;
    TextView gearbox;
    TextView engine_capacity;
    TextView mileage;
    TextView ownership;
    TextView branch;
    TextView agent_Phonenum;
    TextView price;
    Button saveBtn;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;
    String userUID;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_car, container, false);
        String carNum = CarDetailsFragmentArgs.fromBundle(getArguments()).getCarId();

        //firebase referneces
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabase = FirebaseDatabase.getInstance("https://p-i-motors-default-rtdb.europe-west1.firebasedatabase.app/").getReference("uploads");
        userUID = FirebaseAuth.getInstance().getUid();

        car_Type = view.findViewById(R.id.cartype_textview);
        car_Model = view.findViewById(R.id.carmodel_textview);
        car_num = view.findViewById(R.id.carnumber_textview);
        year = view.findViewById(R.id.caryear_textview);
        gearbox = view.findViewById(R.id.cargear_textview);
        engine_capacity = view.findViewById(R.id.carcapacity_textview);
        mileage = view.findViewById(R.id.carmiles_textview);
        ownership = view.findViewById(R.id.carowner_textview);
        branch = view.findViewById(R.id.carbranch_textview);
        agent_Phonenum = view.findViewById(R.id.caragent_textview);

        price = view.findViewById(R.id.carprice_textview);
        car = Model.instance.getCarById(carNum);
        saveBtn = view.findViewById(R.id.saveButton);
        updateDisplay(car);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                updateCarFirebase(car);
            }
        });

        return view;
    }




    private void updateDisplay(Car car) {
        this.car = car;
        car_Type.setText(car.getCar_Type());
        car_Model.setText(car.getCar_Model());
        car_num.setText(car.getCar_num());
        year.setText(car.getYear());
        gearbox.setText(car.getGearbox());
        engine_capacity.setText(car.getEngine_capacity());
        mileage.setText(car.getMileage());
        ownership.setText(car.getOwnership());
        branch.setText(car.getBranch());
        agent_Phonenum.setText(car.getAgent_Phonenum());

        price.setText(car.getPrice());
    }

    private void updateCarFirebase(Car car){

        mDatabase.child(userUID).child(car.getCar_num()).child("agent_Phonenum").setValue(agent_Phonenum.getText().toString());
        mDatabase.child(userUID).child(car.getCar_num()).child("branch").setValue(branch.getText().toString());
        mDatabase.child(userUID).child(car.getCar_num()).child("car_Type").setValue(car_Type.getText().toString());
        mDatabase.child(userUID).child(car.getCar_num()).child("car_Model").setValue(car_Model.getText().toString());
        mDatabase.child(userUID).child(car.getCar_num()).child("price").setValue(price);
        mDatabase.child(userUID).child(car.getCar_num()).child("ownership").setValue(ownership);
        mDatabase.child(userUID).child(car.getCar_num()).child("mileage").setValue(mileage);
        mDatabase.child(userUID).child(car.getCar_num()).child("engine_capacity").setValue(engine_capacity);
        mDatabase.child(userUID).child(car.getCar_num()).child("gearbox").setValue(gearbox);
        progressBar.setVisibility(View.GONE);

        Navigation.findNavController(this.getView()).navigateUp();
    }


}



