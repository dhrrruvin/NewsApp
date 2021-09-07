package com.example.newsapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder>{

    ArrayList<News> items = new ArrayList<>();
    private Context context;

    NewsListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
//        ViewHolder viewHolder = ;

//        view.setOnClickListener();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListAdapter.ViewHolder holder, int position) {
        News currItem = items.get(position);
        holder.title.setText(currItem.getTitle());
        holder.author.setText(currItem.getAuthor());
        Glide.with(context).load(currItem.getImageUrl()).into(holder.newsImage);
        holder.parent.setOnClickListener(view -> {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(context, Uri.parse(currItem.getUrl()));
        });
//        onItemClicked(items.get(holder.getAdapterPosition()));
//        holder.parent.setOnClickListener(view -> Toast.makeText(context, "Item Clicked is "+position , Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateNews(ArrayList<News> updatedNews) {
        items.clear();
        items.addAll(updatedNews);

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView newsImage;
        private TextView title, author;
        private ConstraintLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            newsImage = itemView.findViewById(R.id.newsImage);
            author = itemView.findViewById(R.id.author);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
