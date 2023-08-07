package com.Retrofit.packgs.views.Categories;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.Retrofit.packgs.databinding.ItemFinanceCategoryBinding;
import com.Retrofit.packgs.model.FinanceCategory;
import java.util.ArrayList;

public class CategoryFinanceAdapter extends RecyclerView.Adapter<CategoryFinanceAdapter.ViewHolder> {
    ArrayList<FinanceCategory> financeCategories;
    public CategoryFinanceAdapter(ArrayList<FinanceCategory> financeCategories) {
        this.financeCategories = financeCategories;
    }

    @NonNull
    @Override
    public CategoryFinanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemFinanceCategoryBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryFinanceAdapter.ViewHolder holder, int position) {
        FinanceCategory financeCategory = financeCategories.get(position);
        holder.itemFinanceCategoryBinding.id2.setText("Operation Number:     "+financeCategory.getId());
        holder.itemFinanceCategoryBinding.tvCategoryCount.setText("Number Operations:     "+financeCategory.getActions_count());
        holder.itemFinanceCategoryBinding.tvCategoryMoney.setText("Amount:     "+ financeCategory.getTotal());
        holder.itemFinanceCategoryBinding.tvCategoryName.setText("Category Name:     "+ financeCategory.getName());
    }

    @Override
    public int getItemCount() {
        return financeCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemFinanceCategoryBinding itemFinanceCategoryBinding;
        public ViewHolder(ItemFinanceCategoryBinding itemFinanceCategoryBinding) {
            super(itemFinanceCategoryBinding.getRoot());
            this.itemFinanceCategoryBinding = itemFinanceCategoryBinding;
        }
    }
}
