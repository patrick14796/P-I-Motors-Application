package com.PI_Motors;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.PI_Motors.model.Model;
import com.PI_Motors.model.Car;

import java.util.LinkedList;
import java.util.List;


public class EditCarFragment extends Fragment {
    private List<Car> data = new LinkedList<Car>();
    Car car;
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
    CheckBox carforsale;
    CheckBox carfortrade;
    CheckBox cardiscount;
    CheckBox carloveit;
    EditText carprice;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_car, container, false);
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
        carforsale = view.findViewById(R.id.carforsale_checkbox);
        carfortrade = view.findViewById(R.id.carfortrade_checkbox);
        cardiscount = view.findViewById(R.id.cardiscount_checkbox);
        carloveit = view.findViewById(R.id.carloveit_checkbox);
        carprice = view.findViewById(R.id.carprice_textview);
//
//        Button Save = view.findViewById(R.id.SaveStudentBtn);
//        Button backBtn = view.findViewById(R.id.CancelStudentBtn);
//        Button deleteBtn = view.findViewById(R.id.DeleteStudentBtn);
        //progressBar = view.findViewById(R.id.studentedit_progressbar);
        progressBar.setVisibility(View.VISIBLE);

        String carId = CarDetailsFragmentArgs.fromBundle(getArguments()).getCarId();
        int position = CarDetailsFragmentArgs.fromBundle(getArguments()).getCarPosition();

//        Model.instance.getCarById(carId, (student)->{
//            this.student = student;
//            StudentName.setText(student.getName());
//            StudentId.setText(student.getId());
//            StudentChecked.setChecked(student.isFlag());
//            progressBar.setVisibility(View.GONE);
//        });

//        if (student != null){
//            this.student = student;
//            StudentName.setText(student.getName());
//            StudentId.setText(student.getId());
//            StudentChecked.setChecked(student.isFlag());
//            progressBar.setVisibility(View.GONE);
//        }


//        deleteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Model.instance.DeleteStudent(student,()->{});
//                progressBar.setVisibility(View.GONE);
//                Navigation.findNavController(view).navigate(R.id.action_editStudentFragment_to_studentsListFragment);            }
//        });


//        backBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.action_editStudentFragment_to_studentsListFragment);
//            }
//        });

//        Save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                student.setName(StudentName.getText().toString());
//                student.setId(StudentId.getText().toString());
//                student.setFlag(StudentChecked.isChecked());
//                Model.instance.addStudent(student,()->{
//
//                });
//                progressBar.setVisibility(View.GONE);
//                Navigation.findNavController(view).navigate(R.id.action_editStudentFragment_to_studentsListFragment);
//
//            }
//        });


        return view;
    }

    @Override
    public void   onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.car_edit_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(getActivity(), "Back To Car Details", Toast.LENGTH_LONG).show();
                Navigation.findNavController(this.getView()).navigateUp();
                break;

            case R.id.car_delete_fragment_menu:
                Toast.makeText(getActivity(), "Car Has Been Deleted", Toast.LENGTH_LONG).show();
                break;

            case R.id.car_save_fragment_menu:
                Toast.makeText(getActivity(), "Car Has Been Saved", Toast.LENGTH_LONG).show();
                break;

            case R.id.car_logout_fragment_menu:
                Toast.makeText(getActivity(), "Disconnected from user!", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}