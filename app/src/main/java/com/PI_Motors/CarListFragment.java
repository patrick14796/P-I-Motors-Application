package com.PI_Motors;

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
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.PI_Motors.model.Model;
import com.PI_Motors.model.Car;

import java.util.List;
import java.util.Random;


public class
CarListFragment extends Fragment{
    List<Car> data;
    View view;
    MyAdapter adapter;
    static int[] car_image = {R.id.mercedes_avatar_imv, R.id.volvo_avatar_imv,R.id.fordMustang_avatar_imv};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_car_list, container, false);
        data = Model.instance.getAllCars();

        RecyclerView list = view.findViewById(R.id.carlist_list_rv);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MyAdapter();
        list.setAdapter(adapter);

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
        addBtn.setOnClickListener(Navigation.createNavigateOnClickListener(CarListFragmentDirections.actionCarListFragmentToAddCarFragment()));

        return view;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView CarType;
        TextView CarModel;
        TextView CarPrice;
        ImageView CarImg;


        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            //int index = new Random().nextInt(car_image.length);
            //int selected_car = car_image[index];
            //((ImageView) CarImg.findViewById(selected_car)).setVisibility(View.VISIBLE);
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
