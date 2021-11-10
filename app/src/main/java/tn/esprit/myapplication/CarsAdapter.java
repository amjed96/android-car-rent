package tn.esprit.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.myapplication.database.MyDatabase;
import tn.esprit.myapplication.entity.Car;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarsViewHolder> {
    List<Car> cars;
    Context context;

    MyDatabase mydb;

    public CarsAdapter(List<Car> cars, Context context) {
        this.cars = cars;
        this.context = context;
    }

    @NonNull
    @Override
    public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car,parent,false);
        return new CarsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarsViewHolder holder, int position) {
        mydb = MyDatabase.getDatabase(context);

        Car c = cars.get(position);

        holder.brandTv.setText(c.getBrand());
        holder.editionTv.setText(c.getEdition());
        holder.priceTv.setText(Integer.toString(c.getPrice()));
        holder.engineTv.setText(c.getEngine());

        byte[] imageBytes = c.getCarPic();

        if(imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
            holder.carPicIV.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() { return cars.size(); }

    public class CarsViewHolder extends RecyclerView.ViewHolder {

        TextView brandTv;
        TextView editionTv;
        TextView priceTv;
        TextView engineTv;
        ImageView carPicIV;

        public CarsViewHolder(@NonNull View itemView) {
            super(itemView);

            brandTv = itemView.findViewById(R.id.brandTv);
            editionTv = itemView.findViewById(R.id.editionTv);
            priceTv = itemView.findViewById(R.id.priceTv);
            engineTv = itemView.findViewById(R.id.engineTv);
            carPicIV = itemView.findViewById(R.id.carPicIV);
        }
    }
}
