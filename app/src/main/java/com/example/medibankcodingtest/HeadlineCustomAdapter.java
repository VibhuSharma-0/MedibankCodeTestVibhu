package com.example.medibankcodingtest;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medibankcodingtest.HeadlinesProvider.Headlines;
import com.example.medibankcodingtest.SavedHeadlinesProvider.SavedHeadlines;
import com.example.medibankcodingtest.SavedHeadlinesProvider.SavedHeadlinesViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HeadlineCustomAdapter  extends RecyclerView.Adapter<HeadlineCustomAdapter.HeadlinesViewHolder> {
    private List<Headlines> mHeadlines;
    private List<SavedHeadlines> mSavedHeadlines;
    SavedHeadlinesViewModel savedHeadlinesViewModel;
    View itemView;

    public HeadlineCustomAdapter() {
    }

    @NonNull
    @Override
    public HeadlinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.headlines_recycler_view, parent, false);
        savedHeadlinesViewModel = new ViewModelProvider((ViewModelStoreOwner) itemView.getContext()).get(SavedHeadlinesViewModel.class);
        return new HeadlinesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadlinesViewHolder holder, int position) {
        if(mSavedHeadlines == null) {
            holder.headline_title.setText(mHeadlines.get(position).getHeadline_title());
            holder.headline_author.setText(mHeadlines.get(position).getHeadline_author());
            holder.headline_desc.setText(mHeadlines.get(position).getHeadline_desc());
            Picasso.get()
                    .load(mHeadlines.get(position).getHeadline_thumbnail())
                    .resize(200, 200)
                    .centerCrop()
                    .into(holder.headline_image);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", mHeadlines.get(position).getHeadline_url());
                    bundle.putString("title", mHeadlines.get(position).getHeadline_title());
                    bundle.putString("desc", mHeadlines.get(position).getHeadline_desc());
                    bundle.putString("author", mHeadlines.get(position).getHeadline_author());
                    bundle.putString("thumbnail", mHeadlines.get(position).getHeadline_thumbnail());
                    Navigation.findNavController(itemView).navigate(R.id.action_headlines_nav_to_webFragment2, bundle);
                }
            });
        }
        else{
            holder.headline_title.setText(mSavedHeadlines.get(position).getHeadline_title());
            holder.headline_author.setText(mSavedHeadlines.get(position).getHeadline_author());
            holder.headline_desc.setText(mSavedHeadlines.get(position).getHeadline_desc());
            Picasso.get()
                    .load(mSavedHeadlines.get(position).getHeadline_thumbnail())
                    .resize(200, 200)
                    .centerCrop()
                    .into(holder.headline_image);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", mSavedHeadlines.get(position).getHeadline_url());
                    bundle.putString("title", mSavedHeadlines.get(position).getHeadline_title());
                    bundle.putString("desc", mSavedHeadlines.get(position).getHeadline_desc());
                    bundle.putString("author", mSavedHeadlines.get(position).getHeadline_author());
                    bundle.putString("thumbnail", mSavedHeadlines.get(position).getHeadline_thumbnail());
                    Navigation.findNavController(itemView).navigate(R.id.action_saved_nav_to_webFragment, bundle);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(itemView.getContext().getApplicationContext(),"News Deleted",Toast.LENGTH_LONG).show();
                    int id = mSavedHeadlines.get(position).getHeadline_id();
                    savedHeadlinesViewModel.deleteSavedHeadlines(id);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mHeadlines == null && mSavedHeadlines == null)
            return 0;
        else if(mHeadlines != null && mSavedHeadlines == null) {

            return mHeadlines.size();

        }
        else
            return mSavedHeadlines.size();
    }

    public void setHeadlines(List<Headlines> newData) {
        mHeadlines=newData;
        mSavedHeadlines = null;
    }
    public void setSavedHeadlines(List<SavedHeadlines> newData) {
        mHeadlines=null;
        mSavedHeadlines = newData;
    }

    public class HeadlinesViewHolder extends RecyclerView.ViewHolder {
        public TextView headline_title;
        public TextView headline_author;
        public TextView headline_desc;
        public ImageView headline_image;

        public HeadlinesViewHolder(@NonNull View itemView) {
            super(itemView);
            headline_title = itemView.findViewById(R.id.headline_title);
            headline_author = itemView.findViewById(R.id.headline_author);
            headline_desc = itemView.findViewById(R.id.headline_desc);
            headline_image = itemView.findViewById(R.id.headline_image);
        }

    }
}
