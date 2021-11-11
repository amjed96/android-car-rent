package tn.esprit.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.myapplication.database.MyDatabase;
import tn.esprit.myapplication.entity.User;

public class AdminUsersFragment extends Fragment {

    RecyclerView usersRv;
    UsersAdapter usersAdapter;
    //ImageButton deleteUser;
    List<User> users = new ArrayList<>();

    MyDatabase mydb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_users, container, false);

        mydb = MyDatabase.getDatabase(requireContext());
        users = mydb.userDAO().getAllUser();
        //requireActivity().setContentView(R.layout.activity_main_admin);

        usersRv = view.findViewById(R.id.usersRv);
        //deleteUser = findViewById(R.id.deleteBtn);

        usersAdapter = new UsersAdapter(users,requireContext());

        usersAdapter.setOnItemClickListener(new UsersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                User user = new User();
                int id = users.get(position).getId();
                user.setId(id);

                mydb.userDAO().deleteUser(user);

            }
        });

        usersRv.setAdapter(usersAdapter);

        return view;
    }
}