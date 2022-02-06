package com.PI_Motors;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.PI_Motors.model.Model;
import com.PI_Motors.model.Car;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class AddCarFragment extends Fragment {
    private FirebaseAuth mAuth;
    EditText carType;
    EditText carModel;
    EditText carNumber;
    EditText carYear;
    EditText cargearbox;
    EditText carengine_capacity;
    EditText carmiles;
    EditText carownership;
    EditText carbarnch;
    EditText caragent;
    EditText carprice;
    CheckBox carforsale;
    CheckBox carfortrade;
    CheckBox cardiscount;
    CheckBox carloveit;
    View view;
    ProgressBar progressbar;
    Button saveBtn;
    Button cancelBtn;
    private DatabaseReference mDatabase;
    String userUID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_car, container, false);
        setHasOptionsMenu(true);
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
        carprice = view.findViewById(R.id.carprice_textview);
        carforsale = view.findViewById(R.id.carforsale_checkbox);
        carfortrade = view.findViewById(R.id.carfortrade_checkbox);
        cardiscount = view.findViewById(R.id.cardiscount_checkbox);
        carloveit = view.findViewById(R.id.carloveit_checkbox);
        progressbar = view.findViewById(R.id.main_progressbar);
        progressbar.setVisibility(View.GONE);
        mDatabase = FirebaseDatabase.getInstance("https://p-i-motors-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        saveBtn = view.findViewById(R.id.main_save_btn);
        cancelBtn = view.findViewById(R.id.main_cancel_btn);
        mAuth = FirebaseAuth.getInstance();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        userUID = AddCarFragmentArgs.fromBundle(getArguments()).getUserUID();
        cancelBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_addCarFragment_to_carListFragment));
        return view;
    }


    private void save() {
        progressbar.setVisibility(View.VISIBLE);
        saveBtn.setEnabled(false);
        cancelBtn.setEnabled(false);

        String Type = carType.getText().toString();
        String model = carModel.getText().toString();
        String carnumber = carNumber.getText().toString();
        String year = carYear.getText().toString();
        String gear = cargearbox.getText().toString();
        String engine = carengine_capacity.getText().toString();
        String miles = carmiles.getText().toString();
        String owner = carownership.getText().toString();
        String branch = carbarnch.getText().toString();
        String agent = caragent.getText().toString();
        String price = carprice.getText().toString();
        boolean forsale = carforsale.isChecked();
        boolean frotrade = carfortrade.isChecked();
        boolean discount = cardiscount.isChecked();
        boolean loveit = carloveit.isChecked();
        Log.d("TAG", "saved Car-Type:" + Type + " Model:" + model + " Car-Number:" + carnumber + "Car-Year:" + year + "GearBox:" + gear + "Engine:" + engine + "Car-Miles:" + miles + "Owner:" + owner + "Branch:" + branch + "Agent:" + agent + "Price" + price + "Is For Sale? :" + forsale + "Is For Trade? :" + frotrade + "Is it in Discount? :" + discount + "Is Loved? :" + loveit);
        Car car = new Car(Type, model, carnumber, year, gear, engine, miles, owner, branch, agent, price, forsale, frotrade, discount, loveit);
        Model.instance.addCar(car);
        FirebaseUser user1 = mAuth.getCurrentUser();
        updateUI(user1,car);
        Navigation.findNavController(this.getView()).navigateUp(); /*Need To Remove From Here and insert Car by FireBase */

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(getActivity(), "Back To Car List", Toast.LENGTH_LONG).show();
                Navigation.findNavController(this.getView()).navigateUp();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateUI(FirebaseUser user1, Car user){

        if(user1 != null){
            mDatabase.child("Posts").child(user1.getUid()).setValue(user);
        //    progressBar.setVisibility(View.GONE);

        }else {
            String s = "";
            //   progressBar.setVisibility(View.GONE);
        }
    }
};

