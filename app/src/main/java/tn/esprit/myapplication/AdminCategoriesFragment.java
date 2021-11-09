package tn.esprit.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.myapplication.database.MyDatabase;
import tn.esprit.myapplication.entity.Category;

public class AdminCategoriesFragment extends Fragment {

    RecyclerView categoriesRv;
    CategoriesAdapter categoriesAdapter;
    ImageButton addCategoryBtn;

    List<Category> categories = new ArrayList<>();

    MyDatabase mydb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_categories, container, false);

        mydb = MyDatabase.getDatabase(requireContext());
        categories = mydb.categoryDAO().getAllCategory();

        categoriesRv = view.findViewById(R.id.categoriesRv);
        addCategoryBtn = view.findViewById(R.id.addCategoryBtn);

        categoriesAdapter = new CategoriesAdapter(categories,requireContext());
        categoriesRv.setAdapter(categoriesAdapter);

        addCategoryBtn.setOnClickListener(view1 -> {
            /* Open add category fragment */
        });

        return view;
    }
}