package tn.esprit.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import tn.esprit.myapplication.database.MyDatabase;
import tn.esprit.myapplication.entity.User;

public class UserProfileFragment extends Fragment {

    ImageView profileImg;
    TextView usernameTv;
    TextView usernameTv1;
    TextView emailTv;
    TextView passwordTv;
    ImageButton editImgBtn;

    User user = new User();

    MyDatabase mydb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        profileImg = view.findViewById(R.id.profileImg);
        usernameTv = view.findViewById(R.id.usernameTv);
        usernameTv1 = view.findViewById(R.id.usernameTv1);
        emailTv = view.findViewById(R.id.emailTv);
        passwordTv = view.findViewById(R.id.passwordTv);

        mydb = MyDatabase.getDatabase(requireContext());
        user = mydb.userDAO().getUserById(1);/* HERE */

        byte[] imageBytes = user.getProfilePic();

        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);

        profileImg.setImageBitmap(bitmap);
        usernameTv.setText(user.getUsername());
        usernameTv1.setText(user.getUsername());
        emailTv.setText(user.getEmail());
        passwordTv.setText(user.getPassword());

        editImgBtn.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragHolder,UserProfileEditFragment.newInstance(user)).commit();
        });

        return view;
    }
}