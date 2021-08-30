package com.example.medibankcodingtest;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.service.controls.templates.StatelessTemplate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medibankcodingtest.HeadlinesProvider.HeadlineViewModel;
import com.example.medibankcodingtest.HeadlinesProvider.Headlines;
import com.example.medibankcodingtest.SourcesProvider.SourceViewModel;
import com.example.medibankcodingtest.SourcesProvider.Sources;
import com.google.gson.internal.$Gson$Preconditions;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.medibankcodingtest.R.string.no_source_msg;

public class Headline extends Fragment {
    private HeadlineViewModel mHeadlineViewModel;
    private SourceViewModel mSourceViewModel;
    List<Sources> all_sources;
    String query_string;
    public Headline() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_headlines, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mHeadlineViewModel = new ViewModelProvider(this).get(HeadlineViewModel.class);
        mHeadlineViewModel.deleteAllHeadlines();

        mSourceViewModel = new ViewModelProvider(this).get(SourceViewModel.class);
        all_sources = mSourceViewModel.getmAllSources();
        query_string = getQueryString(all_sources);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        HeadlineCustomAdapter adapter = new HeadlineCustomAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        NewsApiClient newsApiClient = new NewsApiClient("2ff36990a55c4ed9b12879ce9d6ef899");
        if(!query_string.equals("")) {
            newsApiClient.getTopHeadlines(
                    new TopHeadlinesRequest.Builder()
                            .sources(query_string)
                            .language("en")
                            .build(),
                    new NewsApiClient.ArticlesResponseCallback() {
                        @Override
                        public void onSuccess(ArticleResponse response) {
                            for (int i = 0; i <= response.getArticles().size() - 1; i++) {
                                System.out.println(i);
                                String title = response.getArticles().get(i).getTitle();
                                String desc = response.getArticles().get(i).getDescription();
                                String author = response.getArticles().get(i).getAuthor();
                                String thumbnail = response.getArticles().get(i).getUrlToImage();
                                String url = response.getArticles().get(i).getUrl();
                                String source_id = response.getArticles().get(i).getSource().getId();
                                String source_name = response.getArticles().get(i).getSource().getName();

                                Headlines headlines = new Headlines(title, desc, author, thumbnail, url, source_id, source_name);
                                mHeadlineViewModel.insert(headlines);
                            }
                            mHeadlineViewModel.getmAllHeadlines().observe((LifecycleOwner) view.getContext(), newData -> {
                                adapter.setHeadlines(newData);
                                adapter.notifyDataSetChanged();
                            });
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            System.out.println(throwable.getMessage());
                        }
                    }
            );

        }
        else{
            Toast.makeText(getActivity().getApplicationContext(), no_source_msg,Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getQueryString(List<Sources> all_sources) {
        List<String> query_string = new ArrayList<>();
        for(int i = 0;i<=all_sources.size()-1;i++){
            query_string.add(all_sources.get(i).getSource_id());
        }

        Collection<String> elements = query_string;
        return String.join(",", elements);
    }
}