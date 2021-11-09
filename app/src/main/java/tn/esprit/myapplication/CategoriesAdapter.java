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
import tn.esprit.myapplication.entity.Category;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    List<Category> categories;
    Context context;

    MyDatabase mydb;

    public CategoriesAdapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_admin,parent,false);
        return new CategoriesAdapter.CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        mydb = MyDatabase.getDatabase(context);

        Category c = categories.get(position);

        holder.catNameTv.setText(c.getName());
        holder.catNumberTv.setText(c.getId());

        byte[] imageBytes = c.getCatPic();

        if(imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
            holder.catPicIV.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() { return categories.size(); }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder {

        ImageView catPicIV;
        TextView catNameTv;
        TextView catNumberTv;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            catPicIV = itemView.findViewById(R.id.categoryImgview);
            catNameTv = itemView.findViewById(R.id.categoryNameTv);
            catNumberTv = itemView.findViewById(R.id.categoryIdTv);
        }
    }
}
