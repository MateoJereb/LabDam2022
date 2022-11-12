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

    public BusquedaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        busquedaViewModel = new ViewModelProvider(requireActivity()).get(BusquedaViewModel.class);
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
            public void onClick(View view) { onBuscar();}
        });
    }

    private void onRestablecer() {
        binding.tipoSpinner.setSelection(0);
        binding.capacidadEditText.setText(null);
        binding.minPrecioEditText.setText(null);
        binding.maxPrecioEditText.setText(null);
        binding.ciudadSpinner.setSelection(0);
        binding.wifiCheckBox.setChecked(true);
    }

    private void onBuscar(){
        Bundle bundle = new Bundle();
        if(binding.tipoSpinner.getSelectedItemId() != 0) bundle.putSerializable("tipo",(Alojamiento) binding.tipoSpinner.getSelectedItem());
        if(binding.capacidadEditText.getText().length() > 0) bundle.putInt("capacidad",Integer.parseInt(binding.capacidadEditText.getText().toString()));
        if(binding.minPrecioEditText.getText().length() > 0) bundle.putDouble("minPrecio",Double.parseDouble(binding.minPrecioEditText.getText().toString()));
        if(binding.maxPrecioEditText.getText().length() > 0) bundle.putDouble("maxPrecio",Double.parseDouble(binding.maxPrecioEditText.getText().toString()));
        if(binding.ciudadSpinner.getSelectedItemId() != 0) bundle.putSerializable("ciudad",(Ciudad) binding.ciudadSpinner.getSelectedItem());
        if(binding.tipoSpinner.getSelectedItemId() == 0){
            bundle.putBoolean("wifi",binding.wifiCheckBox.isChecked());
        }
        else{
            if(binding.tipoSpinner.getSelectedItem().getClass() == Departamento.class)
                bundle.putBoolean("wifi",binding.wifiCheckBox.isChecked());
        }

        navController.navigate(R.id.action_busquedaFragment_to_resultadoBusquedaFragment,bundle);
    }
}