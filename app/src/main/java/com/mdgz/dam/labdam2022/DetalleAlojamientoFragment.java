package com.mdgz.dam.labdam2022;

import static java.time.temporal.ChronoUnit.DAYS;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.DatePicker;
import android.widget.TextView;

import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;
import com.mdgz.dam.labdam2022.viewmodels.BusquedaViewModel;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleAlojamientoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleAlojamientoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentDetalleAlojamientoBinding binding;
    private NavController navController;
    private BusquedaViewModel viewModel;

    private Alojamiento alojamiento;

    private int yearDesde;
    private int monthDesde;
    private int dayDesde;
    private int yearHasta;
    private int monthHasta;
    private int dayHasta;

    private Double costo;

    public DetalleAlojamientoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleAlojamientoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleAlojamientoFragment newInstance(String param1, String param2) {
        DetalleAlojamientoFragment fragment = new DetalleAlojamientoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("yearDesde",yearDesde);
        outState.putInt("monthDesde",monthDesde);
        outState.putInt("dayDesde",dayDesde);
        outState.putInt("yearHasta",yearHasta);
        outState.putInt("monthHasta",monthHasta);
        outState.putInt("dayHasta",dayHasta);
        outState.putDouble("costo",costo);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            alojamiento = (Alojamiento) getArguments().getSerializable("alojamiento");
        }

        viewModel = new ViewModelProvider(requireActivity()).get(BusquedaViewModel.class);

        if(savedInstanceState != null){
            yearDesde = savedInstanceState.getInt("yearDesde");
            monthDesde = savedInstanceState.getInt("monthDesde");
            dayDesde = savedInstanceState.getInt("dayDesde");
            yearHasta = savedInstanceState.getInt("yearHasta");
            monthHasta = savedInstanceState.getInt("monthHasta");
            dayHasta = savedInstanceState.getInt("dayHasta");
            costo = savedInstanceState.getDouble("costo");
        }
        else{
            Calendar calendar = Calendar.getInstance();
            yearDesde = calendar.get(Calendar.YEAR);
            monthDesde = calendar.get(Calendar.MONTH);
            dayDesde = calendar.get(Calendar.DAY_OF_MONTH);
            yearHasta = calendar.get(Calendar.YEAR);
            monthHasta = calendar.get(Calendar.MONTH);
            dayHasta = calendar.get(Calendar.DAY_OF_MONTH);
            costo = 0.0;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentDetalleAlojamientoBinding.inflate(inflater, container, false);
        navController = NavHostFragment.findNavController(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tituloTextView.setText(alojamiento.getTitulo());
        binding.descripcionTextView.setText(alojamiento.getDescripcion());
        binding.favoritoCheckBox.setChecked(alojamiento.getFavorito());

        String ubicacion = "";
        ViewStub stub = binding.tipoAlojamientoLayout;

        if(alojamiento.getClass() == Habitacion.class) {
            Habitacion hab = (Habitacion) alojamiento;
            ubicacion = hab.getHotel().getNombre() + ". "+hab.getHotel().getUbicacion().getCalle()+" "+hab.getHotel().getUbicacion().getNumero()+", "+hab.getHotel().getUbicacion().getCiudad().getNombre();

            stub.setLayoutResource(R.layout.detalle_habitacion);
            View viewTipo = stub.inflate();

            ((TextView) viewTipo.findViewById(R.id.capacidadTextView)).setText("Capacidad: " + alojamiento.getCapacidad());
            ((TextView) viewTipo.findViewById(R.id.individualesTextView)).setText("Camas individuales: " + hab.getCamasIndividuales());
            ((TextView) viewTipo.findViewById(R.id.matrimonialesTextView)).setText("Camas matrimoniales: " + hab.getCamasMatrimoniales());

            String estacionamiento = "Tiene estacionamiento: ";
            if (hab.getTieneEstacionamiento()) estacionamiento+="Sí";
            else estacionamiento+="No";
            ((TextView) viewTipo.findViewById(R.id.estacionamientoTextView)).setText(estacionamiento);
        }
        else{
            Departamento dpto = (Departamento) alojamiento;
            ubicacion = dpto.getUbicacion().getCalle()+" "+dpto.getUbicacion().getNumero()+", "+dpto.getUbicacion().getCiudad().getNombre();

            stub.setLayoutResource(R.layout.detalle_departamento);
            View viewTipo = stub.inflate();

            ((TextView) viewTipo.findViewById(R.id.capacidadTextView)).setText("Capacidad: " + alojamiento.getCapacidad());

            String wifi = "Incluye WiFi: ";
            if (((Departamento) alojamiento).getTieneWifi()) wifi+="Sí";
            else wifi+="No";
            ((TextView) viewTipo.findViewById(R.id.wifiTextView)).setText(wifi);

            ((TextView) viewTipo.findViewById(R.id.limpiezaTextView)).setText("Costo limpieza: $"+String.format("%.2f",dpto.getCostoLimpieza()));
            ((TextView) viewTipo.findViewById(R.id.habitacionesTextView)).setText("Cantidad de habitaciones: " + dpto.getCantidadHabitaciones());
        }

        binding.precioTextView.setText("$"+String.format("%.2f",costo));

        binding.ubicacionTextView.setText(ubicacion);

        binding.desdeDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(requireActivity(),new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month++;
                        String fecha = day+"/"+month+"/"+year;
                        binding.desdeDatePicker.setText(fecha);

                        yearDesde = year;
                        monthDesde = month-1;
                        dayDesde = day;

                        actualizarCosto();
                    }
                }, yearDesde, monthDesde, dayDesde);

                dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dialog.show();
            }
        });

        binding.hastaDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(requireActivity(),new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month++;
                        String fecha = day+"/"+month+"/"+year;
                        binding.hastaDatePicker.setText(fecha);

                        yearHasta = year;
                        monthHasta = month-1;
                        dayHasta = day;

                        actualizarCosto();
                    }
                }, yearHasta, monthHasta, dayHasta);

                dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dialog.show();
            }
        });

    }

    private void actualizarCosto(){
        if(binding.desdeDatePicker.getText().length() > 0 && binding.hastaDatePicker.getText().length() > 0){
            LocalDate  fechaDesde = LocalDate.of(yearDesde,monthDesde,dayDesde),
                    fechaHasta = LocalDate.of(yearHasta,monthHasta,dayHasta);

            if(fechaDesde.isAfter(fechaHasta)) costo = 0.0;
            else {
                long cantDias = DAYS.between(fechaDesde, fechaHasta);
                if(cantDias == 0) cantDias++;

                costo = cantDias * alojamiento.getPrecioBase();
            }
        }
        else{
            costo = 0.0;
        }

        binding.precioTextView.setText("$"+String.format("%.2f",costo));
    }
}