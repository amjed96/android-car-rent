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
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import tn.esprit.myapplication.database.MyDatabase;
import tn.esprit.myapplication.entity.User;

public class ProfilepicFragment extends Fragment {

    ImageView profilePicIv;
    TextView userTv;
    Button pickImageBtn;
    Button signupBtn;
    User user;

    MyDatabase mydb;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageFilePath;
    private Bitmap imageToStore;

    public static ProfilepicFragment newInstance(User u) {

        ProfilepicFragment fragment = new ProfilepicFragment();
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
        View view = inflater.inflate(R.layout.fragment_profilepic, container, false);

        pickImageBtn = view.findViewById(R.id.pickImageBtn);
        signupBtn = view.findViewById(R.id.signupBtn);
        profilePicIv = view.findViewById(R.id.profilePicIv);
        userTv = view.findViewById(R.id.userTv);
        userTv.setText(user.toString());

        mydb = MyDatabase.getDatabase(requireContext());

        pickImageBtn.setOnClickListener(view1 -> {
            selectImage(view);
        });

        signupBtn.setOnClickListener(view1 -> {
            try{
                mydb.userDAO().insertUser(user);
                Toast.makeText(requireContext(),"Sign up successful ! "+user.toString(),Toast.LENGTH_LONG).show();
            }
            catch (Exception e) {
                Toast.makeText(requireContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
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

                profilePicIv.setImageBitmap(imageToStore);
                //user.setProfilePic(imageToStore);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] imageToBytes;

                imageToStore.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                imageToBytes = byteArrayOutputStream.toByteArray();
                /* ###### */
                Blob blob = new Blob() {
                    @Override
                    public long length() throws SQLException {
                        return 0;
                    }

                    @Override
                    public byte[] getBytes(long pos, int length) throws SQLException {
                        return new byte[0];
                    }

                    @Override
                    public InputStream getBinaryStream() throws SQLException {
                        return null;
                    }

                    @Override
                    public long position(byte[] pattern, long start) throws SQLException {
                        return 0;
                    }

                    @Override
                    public long position(Blob pattern, long start) throws SQLException {
                        return 0;
                    }

                    @Override
                    public int setBytes(long pos, byte[] bytes) throws SQLException {
                        return 0;
                    }

                    @Override
                    public int setBytes(long pos, byte[] bytes, int offset, int len) throws SQLException {
                        return 0;
                    }

                    @Override
                    public OutputStream setBinaryStream(long pos) throws SQLException {
                        return null;
                    }

                    @Override
                    public void truncate(long len) throws SQLException {

                    }

                    @Override
                    public void free() throws SQLException {

                    }

                    @Override
                    public InputStream getBinaryStream(long pos, long length) throws SQLException {
                        return null;
                    }
                };
                blob.setBytes(1,imageToBytes);
                /* ###### */

                user.setProfilePic(imageToBytes);
            }
        }
        catch (Exception e) {
            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}