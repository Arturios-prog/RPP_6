package com.example.androidlab6.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlab6.data.Item;
import com.example.androidlab6.data.ItemData;
import com.example.androidlab6.R;
import com.example.androidlab6.cart.Cart;

public class ClientViewPagerAdapter extends RecyclerView.Adapter<ClientViewPagerAdapter.ViewHolder> {

    Context context;

    public ClientViewPagerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_item_main, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Item item = ItemData.getInstance().getAvailableItems().get(position);
        holder.name.setText(item.getName());
        holder.count.setText(Integer.toString(item.getCount()));
        holder.price.setText(Integer.toString(item.getPrice()));
        holder.description.setText(item.getDescription());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cart.getInstance().getCount(item) < item.getCount())
                {
                    Cart.getInstance().addItem(item);
                } else {
                    Toast.makeText(context, "Not enough", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ItemData.getInstance().getAvailableItems().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        TextView count;
        TextView description;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_pager_main);
            price = itemView.findViewById(R.id.price_pager_main);
            count = itemView.findViewById(R.id.count_pager_main);
            description = itemView.findViewById(R.id.desc_pager_main);
            button = itemView.findViewById(R.id.viewpager_item_btn);
        }
    }
}
