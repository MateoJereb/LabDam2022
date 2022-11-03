package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusquedaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusquedaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentBusquedaBinding binding;
    private NavController navController;
    private BusquedaViewModel viewModel;

    public BusquedaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BusquedaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BusquedaFragment newInstance(String param1, String param2) {
        BusquedaFragment fragment = new BusquedaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        viewModel = new ViewModelProvider(requireActivity()).get(BusquedaViewModel.class);
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

        List<Alojamiento> listaTipos = viewModel.getTiposAlojamiento();
        ArrayAdapter<Alojamiento> adapterTipos = new ArrayAdapter<Alojamiento>(this.getActivity(),android.R.layout.simple_spinner_dropdown_item,listaTipos);
        binding.tipoSpinner.setAdapter(adapterTipos);

        viewModel.getCiudades().observe(getViewLifecycleOwner(), ciudades -> {
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
}