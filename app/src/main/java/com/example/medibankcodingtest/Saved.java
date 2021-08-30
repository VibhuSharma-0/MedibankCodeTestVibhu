package com.example.medibankcodingtest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medibankcodingtest.SavedHeadlinesProvider.SavedHeadlinesViewModel;

import static com.example.medibankcodingtest.R.string.delete_saved_items;

public class Saved extends Fragment {
    SavedHeadlinesViewModel savedHeadlinesViewModel;
    public Saved() {
       }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_saved, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getActivity().getApplicationContext(), delete_saved_items,Toast.LENGTH_LONG).show();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_saved);
        HeadlineCustomAdapter adapter = new HeadlineCustomAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        savedHeadlinesViewModel = new ViewModelProvider(this).get(SavedHeadlinesViewModel.class);
        savedHeadlinesViewModel.getmAllSavedHeadlines().observe((LifecycleOwner) view.getContext(), newData -> {
            adapter.setSavedHeadlines(newData);
            adapter.notifyDataSetChanged();
        });
    }
}