package com.PI_Motors;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.PI_Motors.model.Model;
import com.PI_Motors.model.Car;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Random;


public class
CarListFragment extends Fragment{
    List<Car> data;
    View view;
    MyAdapter adapter;
    String userUID;
    private ProgressBar mProgressCircle;
    private DatabaseReference mDatabaseRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_car_list, container, false);
        mProgressCircle = view.findViewById(R.id.progress_circle);

        data = Model.instance.getAllCars();
        userUID = FirebaseAuth.getInstance().getUid();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads").child(userUID);


        RecyclerView list = view.findViewById(R.id.carlist_list_rv);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MyAdapter();
        list.setAdapter(adapter);


        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Car car = postSnapshot.getValue(Car.class);

                    Log.d("TAG","Looooooooookkkkkkkkkkkkkk hereeeeeeeeeeeeeee!!!!!!!!!!!!!!!!!" + car);
                    data.add(car);
                }

                mProgressCircle.setVisibility(View.INVISIBLE);
                adapter = new MyAdapter();
                list.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });











        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Car car = data.get(position);
                Log.d("TAG","row was clicked " + position);
                CarListFragmentDirections.ActionCarListFragmentToCarDetailsFragment action = CarListFragmentDirections.actionCarListFragmentToCarDetailsFragment(car.getCar_num(),position);
                Navigation.findNavController(v).navigate(action);

            }
        });

        ImageButton addBtn = view.findViewById(R.id.cartlist_add_btn);
        addBtn.setOnClickListener(Navigation.createNavigateOnClickListener(CarListFragmentDirections.actionCarListFragmentToAddCarFragment(userUID)));

        return view;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView CarType;
        TextView CarModel;
        TextView CarPrice;



        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            CarType = itemView.findViewById(R.id.car_type);
            CarModel = itemView.findViewById(R.id.car_model);
            CarPrice = itemView.findViewById(R.id.car_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(pos,v);
                    }
                }
            });
        }
    }


    interface OnItemClickListener{
        void onItemClick(int position, View v);
    }
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        OnItemClickListener listener;
        public void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.car_list_order,parent,false);
            MyViewHolder holder = new MyViewHolder(view,listener);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Car car = data.get(position);
            holder.CarType.setText(car.getCar_Type());
            holder.CarModel.setText(car.getCar_Model());
            holder.CarPrice.setText(car.getPrice());

        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}