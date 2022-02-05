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
import android.widget.Toast;

import com.PI_Motors.model.Model;
import com.PI_Motors.model.Car;

import java.util.LinkedList;
import java.util.List;


public class EditCarFragment extends Fragment {
    private List<Car> data = new LinkedList<Car>();
    Car car;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_car, container, false);
        setHasOptionsMenu(true);
        //EditText StudentName = view.findViewById(R.id.StudentName_edit);
        //EditText StudentId = view.findViewById(R.id.StudentId_edit);
        //CheckBox StudentChecked = view.findViewById(R.id.Student_CheckEdit);
        //Button Save = view.findViewById(R.id.SaveStudentBtn);
        //Button backBtn = view.findViewById(R.id.CancelStudentBtn);
        //Button deleteBtn = view.findViewById(R.id.DeleteStudentBtn);
        //progressBar = view.findViewById(R.id.studentedit_progressbar);
        //progressBar.setVisibility(View.VISIBLE);

        //String studentId = EditStudentFragmentArgs.fromBundle(getArguments()).getStudentId();
        //int position = EditStudentFragmentArgs.fromBundle(getArguments()).getStudentPosition();

        //Model.instance.getStudentById(studentId, (student)->{
          //  this.student = student;
            //StudentName.setText(student.getName());
            //StudentId.setText(student.getId());
            //StudentChecked.setChecked(student.isFlag());
            //progressBar.setVisibility(View.GONE);
        //});

        //if (student != null){
          //  this.student = student;
           // StudentName.setText(student.getName());
            //StudentId.setText(student.getId());
            //StudentChecked.setChecked(student.isFlag());
            //progressBar.setVisibility(View.GONE);
        //}


        //deleteBtn.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View view) {
              //  Model.instance.DeleteStudent(student,()->{});
                //progressBar.setVisibility(View.GONE);
                //Navigation.findNavController(view).navigate(R.id.action_editStudentFragment_to_studentsListFragment);            }
        //});


        //backBtn.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View view) {
             //   Navigation.findNavController(view).navigate(R.id.action_editStudentFragment_to_studentsListFragment);
            //}
        //});

        //Save.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View view) {
              //  student.setName(StudentName.getText().toString());
                //student.setId(StudentId.getText().toString());
                //student.setFlag(StudentChecked.isChecked());
                //Model.instance.addStudent(student,()->{

                //});
                //progressBar.setVisibility(View.GONE);
                //Navigation.findNavController(view).navigate(R.id.action_editStudentFragment_to_studentsListFragment);

            //}
        //});


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