package tn.esprit.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import tn.esprit.myapplication.database.MyDatabase;
import tn.esprit.myapplication.entity.Car;
import tn.esprit.myapplication.entity.Category;
import tn.esprit.myapplication.entity.User;

public class UserCarsFragment extends Fragment {

    RecyclerView categoriesRv;
    RecyclerView carsRv;

    CategoriesAdapterUser categoriesAdapter;
    CarsAdapter carsAdapter;

    List<Category> categories = new ArrayList<>();
    List<Car> cars = new ArrayList<>();

    User user = new User();

    MyDatabase mydb;

    public static UserCarsFragment newInstance(User user) {

        UserCarsFragment fragment = new UserCarsFragment();
        Bundle args = new Bundle();

        args.putSerializable("user",user);

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
        View view = inflater.inflate(R.layout.fragment_user_cars, container, false);

        mydb = MyDatabase.getDatabase(requireContext());

        categories = mydb.categoryDAO().getAllCategory();
        cars = mydb.carDAO().getAllCar();

        categoriesRv = view.findViewById(R.id.categoriesRv);
        carsRv = view.findViewById(R.id.carsRv);

        categoriesAdapter = new CategoriesAdapterUser(categories,requireContext());
        carsAdapter = new CarsAdapter(cars,requireContext());

        /*categoriesAdapter.setOnItemClickListener(new CategoriesAdapterUser.OnItemClicklistener() {
            @Override
            public void onItemClick(int position) {
                cars = mydb.carDAO().getCarsByCategory(categories.get(position).getName());
                carsAdapter = new CarsAdapter(cars,requireContext());
                carsAdapter.notifyDataSetChanged();
                carsRv.setAdapter(carsAdapter);
            }
        });*/

        carsAdapter.setOnClickListener(new CarsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Car car = new Car();
                car = cars.get(position);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragHolder,CarDetailsFragment.newInstance(car,user)).commit();
            }
        });

        categoriesRv.setAdapter(categoriesAdapter);
        carsRv.setAdapter(carsAdapter);

        return view;
    }
}