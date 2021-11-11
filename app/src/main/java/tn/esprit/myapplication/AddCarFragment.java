package tn.esprit.myapplication;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import tn.esprit.myapplication.database.MyDatabase;
import tn.esprit.myapplication.entity.Car;

public class AddCarFragment extends Fragment {

    ImageView carPicIv;
    Button addPicBtn;
    Button addCarBtn;
    TextInputLayout carBrandTIL;
    TextInputLayout carEditionTIL;

    TextInputLayout carPriceTIL;
    TextInputLayout carEngineTIL;
    TextInputLayout carTopSpeedTIL;

    Spinner spinnerCategory;

    Car car = new Car();

    MyDatabase mydb;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageFilePath;
    private Bitmap imageToStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_car, container, false);

        addPicBtn = view.findViewById(R.id.addPicBtn);
        addCarBtn = view.findViewById(R.id.addCarBtn);
        carBrandTIL = view.findViewById(R.id.carBrandTIL);
        carEditionTIL = view.findViewById(R.id.carEditionTIL);

        carPriceTIL = view.findViewById(R.id.carPriceTIL);
        carEngineTIL = view.findViewById(R.id.carEngineTIL);
        carTopSpeedTIL = view.findViewById(R.id.carTopSpeedTIL);

        mydb = MyDatabase.getDatabase(requireContext());

        List<String> categories = mydb.categoryDAO().getCategoryNames();
        spinnerCategory = view.findViewById(R.id.spinnerCategory);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(),R.layout.spinner_layout,R.id.text,categories);
        spinnerCategory.setAdapter(adapter);

        addPicBtn.setOnClickListener(view1 -> {
            selectImage(view);/**/
        });

        addCarBtn.setOnClickListener(view1 -> {
            car.setBrand(carBrandTIL.getEditText().getText().toString());
            car.setEdition(carEditionTIL.getEditText().getText().toString());
            car.setCategory("");
            car.setPrice(Integer.valueOf(carPriceTIL.getEditText().getText().toString()));
            car.setEngine(carEngineTIL.getEditText().getText().toString());
            car.setTopSpeed(Integer.valueOf(carTopSpeedTIL.getEditText().getText().toString()));
            car.setCategory(spinnerCategory.getSelectedItem().toString());
            mydb.carDAO().insertCar(car);
            Toast.makeText(requireContext(),"Car added successfully !",Toast.LENGTH_SHORT);
        });

        return view;
    }

    public void selectImage(View objectView) {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");

            intent.setAction(intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,PICK_IMAGE_REQUEST);
        }
        catch (Exception e){
            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(),imageFilePath);

                carPicIv.setImageBitmap(imageToStore);
                //user.setProfilePic(imageToStore);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] imageToBytes;

                imageToStore.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                imageToBytes = byteArrayOutputStream.toByteArray();

                car.setCarPic(imageToBytes);
            }
        }
        catch (Exception e) {
            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}