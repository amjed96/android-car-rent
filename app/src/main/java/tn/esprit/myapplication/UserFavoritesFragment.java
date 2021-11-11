package tn.esprit.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tn.esprit.myapplication.database.MyDatabase;
import tn.esprit.myapplication.entity.Car;
import tn.esprit.myapplication.entity.User;

public class UserFavoritesFragment extends Fragment {

    RecyclerView carsRv;

    User user;
    List<Car> cars;

    CarsAdapter carsAdapter;

    MyDatabase mydb;

    public static UserFavoritesFragment newInstance(User user) {
        UserFavoritesFragment fragment = new UserFavoritesFragment();
        Bundle args = new Bundle();

        args.putSerializable("user",user);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable("user");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_favorites, container, false);

        mydb = MyDatabase.getDatabase(requireContext());

        cars = mydb.carDAO().getRentCars(user.getId());

        carsRv = view.findViewById(R.id.carsRv);

        carsAdapter = new CarsAdapter(cars,requireContext());

        carsAdapter.setOnClickListener(new CarsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        carsRv.setAdapter(carsAdapter);

        return view;
    }
}