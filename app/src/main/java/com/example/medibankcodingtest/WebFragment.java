package com.example.medibankcodingtest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.medibankcodingtest.SavedHeadlinesProvider.SavedHeadlines;
import com.example.medibankcodingtest.SavedHeadlinesProvider.SavedHeadlinesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class WebFragment extends Fragment {
    String news_url, title, desc, author, thumbnail;
    SavedHeadlinesViewModel savedHeadlinesViewModel;
    public WebFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            news_url = getArguments().getString("url");
            title = getArguments().getString("title");
            desc = getArguments().getString("desc");
            author = getArguments().getString("author");
            thumbnail = getArguments().getString("thumbnail");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        savedHeadlinesViewModel = new ViewModelProvider(this).get(SavedHeadlinesViewModel.class);

        WebView browser = (WebView) view.findViewById(R.id.webView);
        browser.setWebViewClient(new WebViewClient());
        browser.loadUrl(news_url);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton);
        savedHeadlinesViewModel.getmAllSavedHeadlines().observe((LifecycleOwner) view.getContext(), newData -> {
            for (int i = 0; i <=newData.size()-1; i++){
                if(newData.get(i).getHeadline_url().equals(news_url)){
                    floatingActionButton.setVisibility(View.INVISIBLE);
                }
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedHeadlinesViewModel.insert(new SavedHeadlines(title,desc,author,thumbnail,news_url));
                floatingActionButton.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(),"Saved to Favourites.",Toast.LENGTH_LONG).show();
            }
        });
    }

}