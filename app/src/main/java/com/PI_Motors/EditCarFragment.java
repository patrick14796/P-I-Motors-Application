package com.PI_Motors;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.PI_Motors.model.Model;
import com.PI_Motors.model.Car;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;


public class EditCarFragment extends Fragment {
    private List<Car> data = new LinkedList<Car>();
    private Uri editImageUri;
    String Newurl;
    Car car;
    TextView car_Type;
    TextView car_Model;
    TextView car_num;
    TextView year;
    TextView gearbox;
    TextView engine_capacity;
    TextView mileage;
    TextView ownership;
    TextView agent_Phonenum;
    TextView price;
    Button saveBtn;
    Button backBtn;
    Button deleteBtn;
    Button EditImg;
    ImageView carimg;
    ProgressBar progressbar;
    private StorageReference mStorageRef;
    private StorageTask EditTask;
    private DatabaseReference mDatabase;
    String userUID;



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
        agent_Phonenum = view.findViewById(R.id.caragent_textview);
        carimg = view.findViewById(R.id.imageView2);
        price = view.findViewById(R.id.carprice_textview);
        car = Model.instance.getCarById(carNum);
        EditImg = view.findViewById(R.id.EditImg);
        progressbar = view.findViewById(R.id.progressBar2);
        progressbar.setVisibility(View.GONE);
        saveBtn = view.findViewById(R.id.saveButtonEdit);
        backBtn = view.findViewById(R.id.BackButtonEdit);
        deleteBtn = view.findViewById(R.id.DeleteButtonEdit);
        updateDisplay(car);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCarFirebase(car);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoBack();
            }
        });

        EditImg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openSomeActivityForResult();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeData();
            }
        });
        return view;
    }

    public void removeData(){
        //mDatabase.child(userUID).removeValue().equals(this.car.getCar_num());
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query ToRemoveQuery = ref.child("uploads").child(userUID).orderByChild("car_num").equalTo(this.car.getCar_num());
        ToRemoveQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot RemoveSnapshot: dataSnapshot.getChildren()) {
                    RemoveSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG","Error On Delete ");
            }
        });
        Navigation.findNavController(this.getView()).navigateUp();
    }

    public void GoBack(){
        Navigation.findNavController(this.getView()).navigateUp();
    }


    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        editImageUri = data.getData();
                        Picasso.get().load(editImageUri).into(carimg);
                    }
                }
            });

    public void openSomeActivityForResult() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        someActivityResultLauncher.launch(intent);
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
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
        agent_Phonenum.setText(car.getAgent_Phonenum());
        price.setText(car.getPrice());
        Picasso.get()
                .load(car.getCarImageUrl())
                .resize(50, 50)
                .centerCrop()
                .into(carimg);
    }

    private void updateCarFirebase(Car car){

        mDatabase.child(userUID).child(car.getCar_num()).child("agent_Phonenum").setValue(agent_Phonenum.getText().toString());
        mDatabase.child(userUID).child(car.getCar_num()).child("car_Type").setValue(car_Type.getText().toString());
        mDatabase.child(userUID).child(car.getCar_num()).child("car_Model").setValue(car_Model.getText().toString());
        mDatabase.child(userUID).child(car.getCar_num()).child("price").setValue(price.getText().toString());
        mDatabase.child(userUID).child(car.getCar_num()).child("year").setValue(year.getText().toString());
        mDatabase.child(userUID).child(car.getCar_num()).child("ownership").setValue(ownership.getText().toString());
        mDatabase.child(userUID).child(car.getCar_num()).child("mileage").setValue(mileage.getText().toString());
        mDatabase.child(userUID).child(car.getCar_num()).child("engine_capacity").setValue(engine_capacity.getText().toString());
        mDatabase.child(userUID).child(car.getCar_num()).child("gearbox").setValue(gearbox.getText().toString());
        if (editImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(editImageUri));
            String newImgPath = fileReference.toString();
            Log.d("TAG","New Path: " + newImgPath);
            EditTask = fileReference.putFile(editImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                              @Override
                                              public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                              }
                                          }).addOnFailureListener(new OnFailureListener(){
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                            progressbar.setProgress((int) progress);
                        }
                    }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            Task<Uri> urin = FirebaseStorage.getInstance().getReferenceFromUrl(newImgPath).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Log.d("TAG","New Http url: " + uri);
                                    Newurl = uri.toString();
                                    mDatabase.child(userUID).child(car.getCar_num()).child("carImageUrl").setValue(Newurl);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG","Fail");
                                }
                            });
                        }
                    });
            }

        Navigation.findNavController(this.getView()).navigateUp();
    }


}



