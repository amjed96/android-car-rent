package tn.esprit.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import tn.esprit.myapplication.database.MyDatabase;
import tn.esprit.myapplication.entity.User;

public class LoginFragment extends Fragment {

    TextInputLayout usernameLoginTIL;
    TextInputLayout passwordLoginTIL;
    Button loginBtn;
    TextView signupNav;

    List<User> users;

    MyDatabase mydb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        usernameLoginTIL = view.findViewById(R.id.usernameLoginTIL);
        passwordLoginTIL = view.findViewById(R.id.passwordLoginTIL);
        loginBtn = view.findViewById(R.id.loginBtn);
        signupNav = view.findViewById(R.id.signupNav);

        loginBtn.setOnClickListener(view1 -> {
            mydb = MyDatabase.getDatabase(requireContext());

            users = mydb.userDAO().getAllUser();

            boolean exist = false;
            User user = new User();

            for(User u: users){
                if(u.getUsername().equals(usernameLoginTIL.getEditText().getText().toString()) && (u.getPassword().equals(passwordLoginTIL.getEditText().getText().toString()))){
                    exist = true;
                    user = u;
                    break;
                }
            }
            if(exist) {
                Toast.makeText(requireContext(),"Welcome back !",Toast.LENGTH_SHORT).show();
                if(user.getRole().equals("admin")) {
                    Intent intent = new Intent(requireContext(),MainActivityAdmin.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(requireContext(), MainActivityUser.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                }
            } else {
                Toast.makeText(requireContext(),"Check your credentials !",Toast.LENGTH_SHORT).show();
            }
        });

        signupNav.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragHolder,new SignupFragment()).commit();
        });

        return view;
    }
}