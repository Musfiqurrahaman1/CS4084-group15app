package com.example.group15app;

import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealViewHolder> {

    public interface OnDealClickListener { void onDealClick(Deal deal); }

    private final List<Deal> deals;
    private final OnDealClickListener listener;

    public DealAdapter(List<Deal> deals, OnDealClickListener listener) {
        this.deals = deals; this.listener = listener;
    }

    @NonNull
    @Override
    public DealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deal, parent, false);
        return new DealViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DealViewHolder h, int pos) {
        Deal d = deals.get(pos);
        h.tvTitle.setText(d.getTitle());
        h.tvStore.setText(d.getStore());
        h.tvDiscount.setText(d.getDiscount());
        h.tvCategory.setText(d.getCategory());
        h.itemView.setOnClickListener(v -> listener.onDealClick(d));
    }

    @Override public int getItemCount() { return deals.size(); }

    static class DealViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvStore, tvDiscount, tvCategory;
        DealViewHolder(@NonNull View v) {
            super(v);
            tvTitle    = v.findViewById(R.id.tvDealTitle);
            tvStore    = v.findViewById(R.id.tvStoreName);
            tvDiscount = v.findViewById(R.id.tvDiscount);
            tvCategory = v.findViewById(R.id.tvCategory);
        }
    }
}
