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

public class CategoriesAdapterUser extends RecyclerView.Adapter<CategoriesAdapterUser.CategoriesViewHolder> {

    List<Category> categories;
    Context context;

    MyDatabase mydb;

    public CategoriesAdapterUser(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);
        return new CategoriesAdapterUser.CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        mydb = MyDatabase.getDatabase(context);

        Category c = categories.get(position);

        holder.catNameTv.setText(c.getName());
        holder.catNumberTv.setText(Integer.toString(c.getId()));

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

            catPicIV = itemView.findViewById(R.id.catPicIV);
            catNameTv = itemView.findViewById(R.id.catNameTv);
            catNumberTv = itemView.findViewById(R.id.catNumberTv);/**/
        }
    }
}

