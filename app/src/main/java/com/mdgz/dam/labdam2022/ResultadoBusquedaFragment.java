package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mdgz.dam.labdam2022.databinding.FragmentResultadoBusquedaBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.recycler_views.AlojamientosAdapter;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;
import com.mdgz.dam.labdam2022.viewmodels.BusquedaViewModel;

import java.util.ArrayList;
import java.util.List;

public class ResultadoBusquedaFragment extends Fragment {

    private FragmentResultadoBusquedaBinding binding;
    private NavController navController;
    private BusquedaViewModel viewModel;

    private RecyclerView recyclerView;
    private AlojamientosAdapter recyclerAdapter;


    public ResultadoBusquedaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        viewModel = new ViewModelProvider(requireActivity()).get(BusquedaViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentResultadoBusquedaBinding.inflate(inflater, container, false);
        navController = NavHostFragment.findNavController(this);

        View view = binding.getRoot();

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = binding.resultadosRecyclewView;
        recyclerAdapter = new AlojamientosAdapter(new ArrayList<Alojamiento>(), requireActivity());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        List<Alojamiento> listaAloj = AlojamientoRepository._ALOJAMIENTOS;
        recyclerAdapter.setListaAlojamientos(listaAloj);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerAdapter.setOnItemClickListener(new AlojamientosAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Alojamiento item) {
                onSeleccion(item);
            }
        });

        /*viewModel.getAlojamientos().observe(getViewLifecycleOwner(), alojamientos -> {
            recyclerAdapter.setListaAlojamientos(alojamientos);
        });*/

        binding.nuevaBusquedaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {onNuevaBusqueda();}
        });
    }

    private void onSeleccion(Alojamiento aloj){
        Bundle bundle = new Bundle();
        bundle.putSerializable("alojamiento",aloj);
        navController.navigate(R.id.action_resultadoBusquedaFragment_to_detalleAlojamientoFragment,bundle);
    }

    private void onNuevaBusqueda(){
        requireActivity().onBackPressed();
    }
}