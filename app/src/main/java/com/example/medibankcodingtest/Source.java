package com.example.medibankcodingtest;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.medibankcodingtest.SourcesProvider.SourceViewModel;
import com.example.medibankcodingtest.SourcesProvider.Sources;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.SourcesRequest;
import com.kwabenaberko.newsapilib.models.response.SourcesResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Source extends Fragment {
    ListView sources_list;
    ArrayAdapter<String> adapter;
    ArrayList<String> sources_id_list;
    SourceViewModel sourceViewModel;
    Map<String, String> source_id_name_set;
    public Source() {
        }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sources, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        sourceViewModel = new ViewModelProvider(this).get(SourceViewModel.class);
        super.onViewCreated(view, savedInstanceState);
        source_id_name_set = new HashMap<String, String>();
        sources_list = view.findViewById(R.id.list_view);
        sources_id_list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice,sources_id_list);
        sources_list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        sources_list.setAdapter(adapter);
        NewsApiClient newsApiClient = new NewsApiClient("2ff36990a55c4ed9b12879ce9d6ef899");
        newsApiClient.getSources(
                new SourcesRequest.Builder()
                        .language("en")
                        .country("us")
                        .build(),
                new NewsApiClient.SourcesCallback() {
                    @Override
                    public void onSuccess(SourcesResponse response) {
                        for (int i = 0; i <= response.getSources().size() - 1; i++) {
                            sources_id_list.add(response.getSources().get(i).getName());
                            source_id_name_set.put(response.getSources().get(i).getId(),response.getSources().get(i).getName());
                        }
                        List<Sources> all_sources = sourceViewModel.getmAllSources();
                        List<String> name_list = new ArrayList<>();
                        for (int i = 0; i<=all_sources.size()-1;i++) {
                            name_list.add(all_sources.get(i).getSource_name());
                        }
                        for (int i=0; i<=sources_id_list.size()-1;i++){
                            if(name_list.contains(sources_list.getItemAtPosition(i).toString())){
                                sources_list.setItemChecked(i,true);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );


        sources_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String source_name = sources_list.getItemAtPosition(position).toString();
                String source_id = "";
                for (Map.Entry<String, String> id_name : source_id_name_set.entrySet()) {
                    if (id_name.getValue().equals(source_name)) {
                        source_id = id_name.getKey();
                    }
                }
                if(sources_list.isItemChecked(position)){
                   sourceViewModel.insert(new Sources(source_id,source_name));
                }
                else{
                    sourceViewModel.deleteSource(source_id);
                }
            }
        });
    }

}