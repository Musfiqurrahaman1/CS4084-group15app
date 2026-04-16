package com.example.group15app;

import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreVH> {

    public interface OnStoreClick { void onClick(Deal deal); }

    private final List<Deal> deals;
    private final OnStoreClick listener;

    public StoreAdapter(List<Deal> deals, OnStoreClick listener) {
        this.deals = deals; this.listener = listener;
    }

    @NonNull @Override
    public StoreVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store, parent, false);
        return new StoreVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreVH h, int pos) {
        Deal d = deals.get(pos);
        h.tvName.setText(d.getStore());
        h.tvAddress.setText(d.getAddress());
        h.tvEircode.setText("Eircode: " + d.getEircode());
        h.tvDiscount.setText(d.getDiscount());
        h.tvCat.setText(d.getCategory());
        h.itemView.setOnClickListener(v -> listener.onClick(d));
    }

    @Override public int getItemCount() { return deals.size(); }

    static class StoreVH extends RecyclerView.ViewHolder {
        TextView tvName, tvAddress, tvEircode, tvDiscount, tvCat;
        StoreVH(@NonNull View v) {
            super(v);
            tvName    = v.findViewById(R.id.tvStoreName);
            tvAddress = v.findViewById(R.id.tvStoreAddress);
            tvEircode = v.findViewById(R.id.tvEircode);
            tvDiscount = v.findViewById(R.id.tvStoreDiscount);
            tvCat     = v.findViewById(R.id.tvStoreCategory);
        }
    }
}
