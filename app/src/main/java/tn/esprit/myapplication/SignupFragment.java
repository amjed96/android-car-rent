package tn.esprit.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import tn.esprit.myapplication.database.MyDatabase;
import tn.esprit.myapplication.entity.User;

public class SignupFragment extends Fragment {

    TextInputLayout usernameSignupTIL;
    TextInputLayout emailSignupTIL;
    TextInputLayout passwordSignupTIL;
    Button signupBtn;
    TextView loginNav;

    MyDatabase mydb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        usernameSignupTIL = view.findViewById(R.id.usernameSignupTIL);
        emailSignupTIL = view.findViewById(R.id.emailSignupTIL);
        passwordSignupTIL = view.findViewById(R.id.passwordSignupTIL);

        loginNav = view.findViewById(R.id.loginNav);

        signupBtn = view.findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(view1 -> {
            mydb = MyDatabase.getDatabase(requireContext());
            if(usernameSignupTIL.getEditText().getText().toString().equals("") || emailSignupTIL.getEditText().getText().toString().equals("") ||
            passwordSignupTIL.getEditText().getText().toString().equals("")) {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                User user = new User();
                user.setUsername(usernameSignupTIL.getEditText().getText().toString());
                user.setEmail(emailSignupTIL.getEditText().getText().toString());
                user.setPassword(passwordSignupTIL.getEditText().getText().toString());
                user.setRole("user");

                //Pass the user to the fragment
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragHolder, ProfilepicFragment.newInstance(user)).addToBackStack("frag").commit();
            }
        });

        loginNav.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragHolder,new LoginFragment()).commit();
        });

        return view;
    }
}