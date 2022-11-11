package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgz.dam.labdam2022.databinding.FragmentLogBinding;
import com.mdgz.dam.labdam2022.recycler_views.LogAdapter;
import com.mdgz.dam.labdam2022.viewmodels.LogViewModel;

import java.util.ArrayList;
import java.util.List;

public class LogFragment extends Fragment {

    private FragmentLogBinding binding;
    private LogViewModel viewModel;

    private RecyclerView recyclerView;
    private LogAdapter recyclerAdapter;

    public LogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        viewModel = new ViewModelProvider(requireActivity()).get(LogViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLogBinding.inflate(inflater,container,false);

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = binding.lineaRecyclerView;
        recyclerAdapter = new LogAdapter(new ArrayList<String>(),requireActivity());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        List<String> lineas = viewModel.readLogBusquedas();
        recyclerAdapter.setLineas(lineas);
        recyclerView.setAdapter(recyclerAdapter);
    }
}