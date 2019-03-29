package com.example.sabuj.artificialsoft.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sabuj.artificialsoft.R;
import com.example.sabuj.artificialsoft.models.SearchResult;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.UserViewHolder> {
    private Context context;
    private List<SearchResult> userList;
    private OnItemClickListener onItemClickListener;

    public UserDataAdapter(Context context, List<SearchResult> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        SearchResult searchResult = userList.get(position);
        Picasso.with(context)
                .load(searchResult.getImage())
                .into(holder.image);
        holder.name.setText(searchResult.getName());
        holder.designation.setText(searchResult.getWho());
        holder.phone.setText(searchResult.getUser());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView name, designation, phone;
        OnItemClickListener onItemClickListener;

        public UserViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            image = itemView.findViewById(R.id.civImage);
            name = itemView.findViewById(R.id.tvName);
            designation = itemView.findViewById(R.id.tvWho);
            phone = itemView.findViewById(R.id.tvMobile);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
