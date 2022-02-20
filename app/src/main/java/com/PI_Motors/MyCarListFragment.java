package com.PI_Motors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.PI_Motors.model.Car;
import com.PI_Motors.model.Model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class MyCarListFragment extends Fragment {
    List<Car> data;
    View view;
    MyCarListFragment.MyAdapter adapter;
    String userUID;
    private ProgressBar mProgressCircle;
    private DatabaseReference mDatabaseRef;

    private StorageReference mStorageRef;

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

        adapter = new MyCarListFragment.MyAdapter();


        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Car car = postSnapshot.getValue(Car.class);
                        data.add(car);
                }

                mProgressCircle.setVisibility(View.INVISIBLE);

                list.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });











        adapter.setOnItemClickListener(new MyCarListFragment.OnItemClickListener() {
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
        public ImageView imageView;



        public MyViewHolder(@NonNull View itemView, MyCarListFragment.OnItemClickListener listener) {
            super(itemView);
            CarType = itemView.findViewById(R.id.car_type);
            CarModel = itemView.findViewById(R.id.car_model);
            CarPrice = itemView.findViewById(R.id.car_price);
            imageView = itemView.findViewById(R.id.Car_avatar_imv);

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
    class MyAdapter extends RecyclerView.Adapter<MyCarListFragment.MyViewHolder>{

        MyCarListFragment.OnItemClickListener listener;
        public void setOnItemClickListener(MyCarListFragment.OnItemClickListener listener){
            this.listener = listener;
        }

        @NonNull
        @Override
        public MyCarListFragment.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.car_list_order,parent,false);
            MyCarListFragment.MyViewHolder holder = new MyCarListFragment.MyViewHolder(view,listener);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyCarListFragment.MyViewHolder holder, int position) {
            Car car = data.get(position);
            holder.CarType.setText(car.getCar_Type());
            holder.CarModel.setText(car.getCar_Model());
            holder.CarPrice.setText(car.getPrice());

            Log.d("TAG","Car Type: " + car.getCar_Type());
            Log.d("TAG","image car  " + car.getCarImageUrl());
            mStorageRef = FirebaseStorage.getInstance().getReference().child(car.getCarImageUrl());
            try {
                final File localfile = File.createTempFile("car",""+car.getCarImageSuffix());
                mStorageRef.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Log.d("TAG","IMG Load Succsessfuly");
                        Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                        holder.imageView.setImageBitmap(bitmap);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG","Fail To Load IMG!!!");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}