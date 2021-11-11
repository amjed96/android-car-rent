package tn.esprit.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import tn.esprit.myapplication.database.MyDatabase;
import tn.esprit.myapplication.entity.Car;
import tn.esprit.myapplication.entity.User;

public class CarDetailsFragment extends Fragment {

    TextView brandEditionTv;
    ImageView carPicIV;
    TextView engineTv;
    TextView speedTv;
    TextView priceTv;
    Button rentBtn;

    Car car;
    User user;

    MyDatabase mydb;

    public static CarDetailsFragment newInstance(Car c, User u) {

        CarDetailsFragment fragment = new CarDetailsFragment();
        Bundle args = new Bundle();

        args.putSerializable("car",c);
        args.putSerializable("user",u);

        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            car = (Car) getArguments().getSerializable("car");
            user = (User) getArguments().getSerializable("user");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_details, container, false);

        brandEditionTv = view.findViewById(R.id.brandEditionTv);
        carPicIV = view.findViewById(R.id.carPicIV);
        engineTv = view.findViewById(R.id.engineTv);
        speedTv = view.findViewById(R.id.speedTv);
        priceTv = view.findViewById(R.id.priceTv);
        rentBtn = view.findViewById(R.id.rentBtn);

        brandEditionTv.setText(car.getBrand()+" "+car.getEdition());
        engineTv.setText(car.getEngine());
        speedTv.setText(Integer.toString(car.getTopSpeed()));
        priceTv.setText(Integer.toString(car.getPrice()));

        byte[] imageBytes = car.getCarPic();

        if(imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
            carPicIV.setImageBitmap(bitmap);
        }

        rentBtn.setOnClickListener(view1 -> {
            mydb = MyDatabase.getDatabase(requireContext());
            mydb.carDAO().rentCar(user.getId());
            Toast.makeText(requireContext(),"Car rent successfully !",Toast.LENGTH_SHORT);
        });

        return view;
    }
}