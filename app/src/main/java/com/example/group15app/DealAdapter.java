package com.example.group15app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealViewHolder> {

    public interface OnDealClickListener {
        void onDealClick(Deal deal);
    }

    private List<Deal> deals;
    private OnDealClickListener listener;

    public DealAdapter(List<Deal> deals, OnDealClickListener listener) {
        this.deals    = deals;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_deal, parent, false);
        return new DealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DealViewHolder holder, int position) {
        Deal deal = deals.get(position);
        holder.tvTitle.setText(deal.getTitle());
        holder.tvStore.setText(deal.getStore());
        holder.tvDiscount.setText(deal.getDiscount());
        holder.tvCategory.setText(deal.getCategory());
        holder.itemView.setOnClickListener(v -> listener.onDealClick(deal));
    }

    @Override
    public int getItemCount() { return deals.size(); }

    static class DealViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvStore, tvDiscount, tvCategory;

        DealViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle    = itemView.findViewById(R.id.tvDealTitle);
            tvStore    = itemView.findViewById(R.id.tvStoreName);
            tvDiscount = itemView.findViewById(R.id.tvDiscount);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }
}
