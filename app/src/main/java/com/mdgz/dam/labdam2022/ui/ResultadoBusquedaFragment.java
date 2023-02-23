package com.mdgz.dam.labdam2022.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.databinding.FragmentResultadoBusquedaBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.recycler_views.AlojamientosAdapter;
import com.mdgz.dam.labdam2022.viewmodels.BusquedaViewModel;
import com.mdgz.dam.labdam2022.viewmodels.BusquedaViewModelFactory;
import com.mdgz.dam.labdam2022.viewmodels.LogViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.UUID;

public class ResultadoBusquedaFragment extends Fragment {

    private FragmentResultadoBusquedaBinding binding;
    private NavController navController;
    private BusquedaViewModel viewModel;
    private LogViewModel logViewModel;

    private RecyclerView recyclerView;
    private AlojamientosAdapter recyclerAdapter;
    private List<Alojamiento> listaAloj;

    private Optional<Alojamiento> tipo = Optional.ofNullable(null);
    private Optional<Integer> capacidad = Optional.ofNullable(null);
    private Optional<Double> minPrecio = Optional.ofNullable(null);
    private Optional<Double> maxPrecio = Optional.ofNullable(null);
    private Optional<Ciudad> ciudad = Optional.ofNullable(null);
    private Optional<Boolean> wifi = Optional.ofNullable(null);


    public ResultadoBusquedaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            if(getArguments().getSerializable("tipo") != null) tipo = Optional.of((Alojamiento) getArguments().getSerializable("tipo"));
            if(getArguments().get("capacidad") != null) capacidad = Optional.of(getArguments().getInt("capacidad"));
            if(getArguments().get("minPrecio") != null) minPrecio = Optional.of(getArguments().getDouble("minPrecio"));
            if(getArguments().get("maxPrecio") != null) maxPrecio = Optional.of(getArguments().getDouble("maxPrecio"));
            if(getArguments().get("ciudad") != null) ciudad = Optional.of((Ciudad) getArguments().getSerializable("ciudad"));
            if(getArguments().get("wifi") != null) wifi = Optional.of(getArguments().getBoolean("wifi"));
        }

        viewModel = new ViewModelProvider(requireActivity(), new BusquedaViewModelFactory(getContext())).get(BusquedaViewModel.class);
        logViewModel = new ViewModelProvider(requireActivity()).get(LogViewModel.class);
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

        recyclerAdapter.setListaAlojamientos(new ArrayList<>());
        recyclerView.setAdapter(recyclerAdapter);

        recyclerAdapter.setOnItemClickListener(new AlojamientosAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Alojamiento item) {
                onSeleccion(item);
            }
        });

        recyclerAdapter.setOnCheckedChangedListener(new AlojamientosAdapter.OnCheckedChangedListener() {
            @Override
            public void onCheckedChanged(Alojamiento item, Boolean newValue) {
                onItemFaved(item,newValue);
            }
        });

        binding.nuevaBusquedaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {onNuevaBusqueda();}
        });

        viewModel.getCargado().observe(requireActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean value) {
                if(value){
                    listaAloj = viewModel.getAlojamientos();
                    long tiempoBusqueda = viewModel.getTiempo();
                    registrarBusqueda(listaAloj.size(),tiempoBusqueda);

                    recyclerAdapter.setListaAlojamientos(listaAloj);
                    recyclerView.setAdapter(recyclerAdapter);
                    recyclerAdapter.notifyDataSetChanged();

                    Log.d("Busqueda completa",""+tiempoBusqueda+" ms");
                }
            }
        });

        viewModel.getFavPost().observe(requireActivity(), new Observer<LinkedHashMap<UUID, Boolean>>() {
            @Override
            public void onChanged(LinkedHashMap<UUID, Boolean> favPost) {
                for(Alojamiento a : listaAloj){
                    if(favPost.get(a.getId()) != null){
                        a.setFavorito(favPost.get(a.getId()));
                    }
                }

                recyclerAdapter.setListaAlojamientos(listaAloj);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    private void onSeleccion(Alojamiento aloj){
        Bundle bundle = new Bundle();
        bundle.putSerializable("alojamiento",aloj);
        navController.navigate(R.id.action_resultadoBusquedaFragment_to_detalleAlojamientoFragment,bundle);
    }

    private void onItemFaved(Alojamiento aloj, Boolean newValue){
        new Thread(() -> {
            if(newValue) viewModel.marcarFavorito(aloj);
            else viewModel.desmarcarFavorito(aloj);
        }).start();
    }

    private void onNuevaBusqueda(){
        requireActivity().onBackPressed();
    }

    private void registrarBusqueda(int cant, long tiempo){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireActivity());
        if(preferences.getBoolean("info_uso",false)){
            String log = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"\n"+
                    "Búsqueda realizada \n";

            if(tipo.isPresent()) log+="   -Tipo: "+tipo.get().toString()+"\n";
            if(capacidad.isPresent()) log+="   -Capacidad: "+capacidad.get()+"\n";
            if(minPrecio.isPresent()) log+="   -Precio min: "+minPrecio.get()+"\n";
            if(maxPrecio.isPresent()) log+="   -Precio max: "+maxPrecio.get()+"\n";
            if(ciudad.isPresent()) log+="   -Ciudad: "+ciudad.get().getNombre()+"\n";
            if(wifi.isPresent()){
                if(wifi.get()) log+="   -Incluye WiFi: Si"+"\n";
                else log+="   -Incluye WiFi: No"+"\n";
            }

            log+= "Resultados: "+cant+"\n"+
                  "Tiempo de búsqueda: "+tiempo+"ms\n";

            logViewModel.writeLogBusquedas(log);
        }

    }
}