package com.mdgz.dam.labdam2022;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaBinding;
import com.mdgz.dam.labdam2022.model.*;
import com.mdgz.dam.labdam2022.repo.CiudadRepository;
import com.mdgz.dam.labdam2022.viewmodels.BusquedaViewModel;
import com.mdgz.dam.labdam2022.viewmodels.LogViewModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BusquedaFragment extends Fragment {

    private FragmentBusquedaBinding binding;
    private NavController navController;
    private BusquedaViewModel busquedaViewModel;
    private LogViewModel logViewModel;

    public BusquedaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        busquedaViewModel = new ViewModelProvider(requireActivity()).get(BusquedaViewModel.class);
        logViewModel = new ViewModelProvider(requireActivity()).get(LogViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBusquedaBinding.inflate(inflater,container,false);
        navController = NavHostFragment.findNavController(this);

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Alojamiento> listaTipos = busquedaViewModel.getTiposAlojamiento();
        ArrayAdapter<Alojamiento> adapterTipos = new ArrayAdapter<Alojamiento>(this.getActivity(),android.R.layout.simple_spinner_dropdown_item,listaTipos);
        binding.tipoSpinner.setAdapter(adapterTipos);

        busquedaViewModel.getCiudades().observe(getViewLifecycleOwner(), ciudades -> {
            ArrayAdapter<Ciudad> adapterCiudades = new ArrayAdapter<>(this.getActivity(),android.R.layout.simple_spinner_dropdown_item,ciudades);
            binding.ciudadSpinner.setAdapter(adapterCiudades);
        });

        binding.tipoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Alojamiento tipo = (Alojamiento) adapterView.getItemAtPosition(i);
                if(tipo != null){
                    if(tipo.getClass() == Departamento.class) {
                        binding.wifiCheckBox.setEnabled(true);
                    }
                    else {
                        binding.wifiCheckBox.setEnabled(false);
                    }
                }
                else
                    binding.wifiCheckBox.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.restablecerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onRestablecer();
            }
        });

        binding.buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireActivity());
                if(preferences.getBoolean("info_uso",false))
                    registrarBusqueda();

                navController.navigate(R.id.action_busquedaFragment_to_resultadoBusquedaFragment);
            }
        });
    }

    private void onRestablecer() {
        binding.tipoSpinner.setSelection(0);
        binding.capacidadEditText.setText(null);
        binding.minPrecioEditText.setText(null);
        binding.maxPrecioEditText.setText(null);
        binding.ciudadSpinner.setSelection(0);
        binding.wifiCheckBox.setChecked(false);
    }

    private void registrarBusqueda(){
        String log = "Búsqueda realizada \n" +
                     LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"\n";

        if(binding.tipoSpinner.getSelectedItemId() != 0) log+="Tipo: "+((Alojamiento) binding.tipoSpinner.getSelectedItem()).toString()+"\n";
        if(binding.capacidadEditText.getText().length() > 0) log+="Capacidad: "+binding.capacidadEditText.getText().toString()+"\n";
        if(binding.minPrecioEditText.getText().length() > 0) log+="Precio min: "+binding.minPrecioEditText.getText().toString()+"\n";
        if(binding.maxPrecioEditText.getText().length() > 0) log+="Precio max: "+binding.maxPrecioEditText.getText().toString()+"\n";
        if(binding.ciudadSpinner.getSelectedItemId() != 0) log+="Ciudad: "+((Ciudad) binding.ciudadSpinner.getSelectedItem()).getNombre()+"\n";
        if(binding.tipoSpinner.getSelectedItem().getClass() == Departamento.class) {
            if (binding.wifiCheckBox.isChecked()) log += "Incluye WiFi: Sí\n";
            else log += "Incluye WiFi: No\n";
        }

        //TODO Cantidad de resultados y tiempo de busqueda

        logViewModel.writeLogBusquedas(log);
    }
}