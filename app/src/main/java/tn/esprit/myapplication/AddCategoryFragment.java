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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;

import tn.esprit.myapplication.database.MyDatabase;
import tn.esprit.myapplication.entity.Category;

public class AddCategoryFragment extends Fragment {

    TextInputLayout catNameTIL;
    Button addPicBtn;
    Button addCatBtn;
    ImageView catPicIv;

    Category category = new Category();

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
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);

        catNameTIL = view.findViewById(R.id.catNameTIL);

        addPicBtn = view.findViewById(R.id.addPicBtn);
        addCatBtn = view.findViewById(R.id.addCatBtn);

        mydb = MyDatabase.getDatabase(requireContext());

        addPicBtn.setOnClickListener(view1 -> {
            selectImage(view);/**/
        });

        addCatBtn.setOnClickListener(view1 -> {
            category.setName(catNameTIL.getEditText().getText().toString());
            mydb.categoryDAO().insertCategory(category);
            Toast.makeText(requireContext(),"Category added successfully !",Toast.LENGTH_SHORT);
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

                catPicIv.setImageBitmap(imageToStore);
                //user.setProfilePic(imageToStore);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] imageToBytes;

                imageToStore.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                imageToBytes = byteArrayOutputStream.toByteArray();

                category.setCatPic(imageToBytes);
            }
        }
        catch (Exception e) {
            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}