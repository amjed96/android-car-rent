package tn.esprit.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.myapplication.database.MyDatabase;
import tn.esprit.myapplication.entity.Car;

public class AdminCarsFragment extends Fragment {

    RecyclerView carsRv;
    CarsAdapter carsAdapter;
    ImageButton addCarBtn;

    List<Car> cars = new ArrayList<>();

    MyDatabase mydb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_cars, container, false);

        mydb = MyDatabase.getDatabase(requireContext());
        cars = mydb.carDAO().getAllCar();

        carsRv = view.findViewById(R.id.carsRv);
        addCarBtn = view.findViewById(R.id.addCarBtn);

        carsAdapter = new CarsAdapter(cars,requireContext());
        carsRv.setAdapter(carsAdapter);

        addCarBtn.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragHolder,new AddCarFragment()).commit();
        });

        return view;
    }
}