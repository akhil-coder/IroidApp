package com.example.iroidapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.iroidapp.R;
import com.example.iroidapp.models.FreshProducts;

import java.util.List;

public class ProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerAdapter.ViewHolder> {

    FreshProducts[] freshProducts;
    Context context;

    public ProductsRecyclerAdapter(FreshProducts[] freshProducts, Context context) {
        this.freshProducts = freshProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_new_products_list_item, parent, false);
        return new ProductsRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productName.setText(freshProducts[position].getName());
        holder.productPrice.setText(String.valueOf(freshProducts[position].getPrice()));
        Glide.with(context)
                .asBitmap()
                .load(freshProducts[position].getImage())
                .into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return freshProducts.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView productName, productPrice;
        ImageView productImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.iv_product);
            productName = itemView.findViewById(R.id.tv_product_name);
            productPrice = itemView.findViewById(R.id.tv_product_price);
        }
    }
}
