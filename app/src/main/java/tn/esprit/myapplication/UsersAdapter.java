package tn.esprit.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.myapplication.database.MyDatabase;
import tn.esprit.myapplication.entity.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    List<User> users;
    Context context;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    OnItemClickListener mListener;

    MyDatabase mydb;

    public UsersAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user,parent,false);
        return new UsersViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        mydb = MyDatabase.getDatabase(context);

        User u = users.get(position);

        holder.usernameTv.setText(u.getUsername());
        holder.emailTv.setText(u.getEmail());

        byte[] imageBytes = u.getProfilePic();

        if(imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
            holder.profileImg.setImageBitmap(bitmap);
        }

        /*holder.deleteBtn.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Confirmation");
            builder.setMessage("Are you sure to delete this user ?");
            builder.setPositiveButton("Yes",null);
            builder.setNegativeButton("No",null);

            builder.show();

        });*/
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {

        ImageView profileImg;
        TextView usernameTv;
        TextView emailTv;
        ImageButton deleteBtn;

        public UsersViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            
            profileImg = itemView.findViewById(R.id.profileImgview);
            usernameTv = itemView.findViewById(R.id.usernameTv);
            emailTv = itemView.findViewById(R.id.emailTv);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {

                        }
                    }
                }
            });
            
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                            users = mydb.userDAO().getAllUser();
                            notifyItemRemoved(position);
                        }
                    }

                }
            });
        }
    }
}
