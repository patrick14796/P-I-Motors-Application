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
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.os.Handler;
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
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.PI_Motors.model.Model;
import com.PI_Motors.model.Car;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class AddCarFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    private FirebaseAuth mAuth;
    private StorageTask mUploadTask;
    private StorageReference mStorageRef;
    private ProgressBar mProgressBar;

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

    Button AddImage;
    View view;

    private Uri mImageUri;
    private ImageView mImageView;
    ProgressBar progressbar;
    Button saveBtn;
    Button cancelBtn;
    private DatabaseReference mDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_car, container, false);
        setHasOptionsMenu(true);
        mProgressBar = view.findViewById(R.id.progress_bar);
        mImageView = view.findViewById(R.id.imageView2);
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

        progressbar = view.findViewById(R.id.main_progressbar);
        progressbar.setVisibility(View.GONE);
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");;
        mDatabase = FirebaseDatabase.getInstance("https://p-i-motors-default-rtdb.europe-west1.firebasedatabase.app/").getReference("uploads");
        saveBtn = view.findViewById(R.id.main_save_btn);
        cancelBtn = view.findViewById(R.id.main_cancel_btn);
        mAuth = FirebaseAuth.getInstance();
        AddImage = view.findViewById(R.id.uploadImage);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
        cancelBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_addCarFragment_to_carListFragment));

        AddImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openSomeActivityForResult();
            }
        });


        return view;
    }


    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        mImageUri = data.getData();
                        Picasso.get().load(mImageUri).into(mImageView);
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



    private void uploadFile() {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));
            String IMGPATH = fileReference.toString();
            String newIMG =IMGPATH.substring(28,IMGPATH.length());
            String suffix = IMGPATH.substring(50,IMGPATH.length());
            Log.d("TAG","Img Path " + newIMG);
            Log.d("TAG","Img suffix " + suffix);
            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);
                            progressbar.setVisibility(View.VISIBLE);
                            saveBtn.setEnabled(false);
                            cancelBtn.setEnabled(false);

                            String car_Type = carType.getText().toString();
                            String car_Model = carModel.getText().toString();
                            String car_num = carNumber.getText().toString();
                            String year = carYear.getText().toString();
                            String Gearbox = cargearbox.getText().toString();
                            String Engine_capacity = carengine_capacity.getText().toString();
                            String Mileage = carmiles.getText().toString();
                            String ownership = carownership.getText().toString();
                            String Branch = carbarnch.getText().toString();
                            String Agent_Phonenum = caragent.getText().toString();
                            String Price = carprice.getText().toString();

                            Car car = new Car(car_Type, car_Model, car_num, year, Gearbox, Engine_capacity, Mileage, ownership, Branch, Agent_Phonenum, Price, newIMG,suffix);
                            Model.instance.addCar(car);
                            FirebaseUser user1 = mAuth.getCurrentUser();
                            if(user1 != null){
                                String uploadId = mDatabase.push().getKey();
                                mDatabase.child(user1.getUid()).child(car.getCar_num()).setValue(car);
                                progressbar.setVisibility(View.GONE);

                            }else {
                                progressbar.setVisibility(View.GONE);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                             Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });
            Navigation.findNavController(this.getView()).navigateUp(); /*Need To Remove From Here and insert Car by FireBase */
        } else {
            Toast.makeText(getActivity(), "No file selected", Toast.LENGTH_SHORT).show();
        }
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


};

