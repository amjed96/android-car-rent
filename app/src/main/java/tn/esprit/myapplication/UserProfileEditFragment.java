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
import tn.esprit.myapplication.entity.User;


public class UserProfileEditFragment extends Fragment {

    ImageView profilePicIv;
    TextInputLayout usernameEditTIL;
    TextInputLayout emailEditTIL;
    TextInputLayout passwordEditTIL;
    Button saveEditBtn;
    User user;

    MyDatabase mydb;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageFilePath;
    private Bitmap imageToStore;

    public static UserProfileEditFragment newInstance(User u) {

        UserProfileEditFragment fragment = new UserProfileEditFragment();
        Bundle args = new Bundle();

        args.putSerializable("user",u);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            user = (User) getArguments().getSerializable("user");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile_edit, container, false);

        profilePicIv = view.findViewById(R.id.profilePicIv);
        usernameEditTIL = view.findViewById(R.id.usernameEditTIL);
        emailEditTIL = view.findViewById(R.id.emailEditTIL);
        passwordEditTIL = view.findViewById(R.id.passwordEditTIL);
        saveEditBtn = view.findViewById(R.id.saveEditBtn);

        mydb = MyDatabase.getDatabase(requireContext());

        /* */
        //profilePicIv.setImageBitmap();

        usernameEditTIL.getEditText().setText(user.getUsername());
        emailEditTIL.getEditText().setText(user.getEmail());
        passwordEditTIL.getEditText().setText(user.getPassword());

        profilePicIv.setOnClickListener(view1 -> {
            selectImage(view);
        });

        saveEditBtn.setOnClickListener(view1 -> {

            user.setUsername(usernameEditTIL.getEditText().getText().toString());
            user.setEmail(emailEditTIL.getEditText().getText().toString());
            user.setPassword(passwordEditTIL.getEditText().getText().toString());

            mydb.userDAO().editUser(user.getId(),user.getUsername(),user.getEmail(),user.getPassword());
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragHolder,UserProfileFragment.newInstance(user)).commit();
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
        try{
            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(),imageFilePath);

                profilePicIv.setImageBitmap(imageToStore);
                //user.setProfilePic(imageToStore);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] imageToBytes;

                imageToStore.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                imageToBytes = byteArrayOutputStream.toByteArray();

                user.setProfilePic(imageToBytes);
            }
        }
        catch (Exception e) {
            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}