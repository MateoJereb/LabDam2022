package com.mdgz.dam.labdam2022.ui;

import static java.time.temporal.ChronoUnit.DAYS;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;
import com.mdgz.dam.labdam2022.repo.UserRepository;
import com.mdgz.dam.labdam2022.viewmodels.BusquedaViewModel;
import com.mdgz.dam.labdam2022.viewmodels.BusquedaViewModelFactory;
import com.mdgz.dam.labdam2022.viewmodels.ReservaViewModel;
import com.mdgz.dam.labdam2022.viewmodels.ReservaViewModelFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class DetalleAlojamientoFragment extends Fragment {

    private FragmentDetalleAlojamientoBinding binding;
    private NavController navController;
    private BusquedaViewModel viewModel;
    private ReservaViewModel reservaViewModel;

    private Alojamiento alojamiento;

    private int yearDesde;
    private int monthDesde;
    private int dayDesde;
    private int yearHasta;
    private int monthHasta;
    private int dayHasta;

    private Double costo;

    private Boolean desdeSelected;
    private Boolean hastaSelected;

    private Boolean showingLastDialog;

    public DetalleAlojamientoFragment() {
        // Required empty public constructor
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
        outState.putBoolean("desdeSelected",desdeSelected);
        outState.putBoolean("hastaSelected",hastaSelected);
        outState.putBoolean("showingLastDialog",showingLastDialog);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            alojamiento = (Alojamiento) getArguments().getSerializable("alojamiento");
        }

        viewModel = new ViewModelProvider(requireActivity(), new BusquedaViewModelFactory(getContext())).get(BusquedaViewModel.class);
        reservaViewModel = new ViewModelProvider(requireActivity(), new ReservaViewModelFactory(getContext())).get(ReservaViewModel.class);

        if(savedInstanceState != null){
            yearDesde = savedInstanceState.getInt("yearDesde");
            monthDesde = savedInstanceState.getInt("monthDesde");
            dayDesde = savedInstanceState.getInt("dayDesde");
            yearHasta = savedInstanceState.getInt("yearHasta");
            monthHasta = savedInstanceState.getInt("monthHasta");
            dayHasta = savedInstanceState.getInt("dayHasta");
            costo = savedInstanceState.getDouble("costo");
            desdeSelected = savedInstanceState.getBoolean("desdeSelected");
            hastaSelected = savedInstanceState.getBoolean("hastaSelected");
            showingLastDialog = savedInstanceState.getBoolean("showingLastDialog");

            if(showingLastDialog)
                finalizarReserva();
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
            desdeSelected = false;
            hastaSelected = false;
            showingLastDialog = false;
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

        binding.imageView.setImageResource(alojamiento.getImagen());
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
                        String fecha = day+"/"+(month+1)+"/"+year;
                        binding.desdeDatePicker.setText(fecha);

                        yearDesde = year;
                        monthDesde = month;
                        dayDesde = day;

                        actualizarCosto();
                        desdeSelected = true;
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
                        String fecha = day+"/"+(month+1)+"/"+year;
                        binding.hastaDatePicker.setText(fecha);

                        yearHasta = year;
                        monthHasta = month;
                        dayHasta = day;

                        actualizarCosto();
                        hastaSelected = true;
                    }
                }, yearHasta, monthHasta, dayHasta);

                dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dialog.show();
            }
        });

        binding.favoritoCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean newValue) {
                new Thread(() -> {
                    if(newValue) viewModel.marcarFavorito(alojamiento);
                    else viewModel.desmarcarFavorito(alojamiento);
                }).start();
            }
        });

        binding.reservarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onReservar(); }
        });

        reservaViewModel.getReservaRealizada().observe(requireActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean reservaRealizada) {
                if(reservaRealizada)
                    finalizarReserva();
            }
        });

    }

    private void actualizarCosto(){
        if(binding.desdeDatePicker.getText().length() > 0 && binding.hastaDatePicker.getText().length() > 0){
            LocalDate  fechaDesde = LocalDate.of(yearDesde,monthDesde+1,dayDesde),
                       fechaHasta = LocalDate.of(yearHasta,monthHasta+1,dayHasta);

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

    private void onReservar(){
       if(camposCompletos()){
           if(fechasValidas()){
               if(cantidadValida()){
                   Reserva res = new Reserva(  UUID.randomUUID(),
                           new GregorianCalendar(yearDesde,monthDesde,dayDesde).getTime(),
                           new GregorianCalendar(yearHasta,monthHasta,dayHasta).getTime(),
                           false,
                           Integer.parseInt(binding.cantidadEditText.getText().toString()),
                           costo,
                           alojamiento.getId(),
                           UserRepository.currentUserId());

                   new Thread(() -> {
                       reservaViewModel.reservar(res);
                   }).start();
               }
               else{
                   String msje = " tiene capacidad para 1 a "+alojamiento.getCapacidad()+" personas";
                   if(alojamiento.getClass() == Departamento.class) msje = "El departamento" + msje;
                   else msje = "La habitación" + msje;

                   error(msje);
               }
           }
           else{ error("Ingrese un rango de fechas valido"); }
       }
       else{ error("Complete todos los campos para realizar la reserva"); }
    }

    private Boolean camposCompletos(){
        if(!desdeSelected || !hastaSelected)
            return false;

        if(binding.cantidadEditText.getText().toString().length() == 0)
            return false;

        return true;
    }

    private Boolean fechasValidas(){
        LocalDate  fechaDesde = LocalDate.of(yearDesde,monthDesde+1,dayDesde),
                fechaHasta = LocalDate.of(yearHasta,monthHasta+1,dayHasta);

        if(fechaDesde.isAfter(fechaHasta))
            return false;

        return true;
    }

    private Boolean cantidadValida(){
        Integer cant = Integer.parseInt(binding.cantidadEditText.getText().toString());
        if(cant < 1 || cant > alojamiento.getCapacidad())
            return false;

        return true;
    }

    private void error(String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Error!")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar",null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void finalizarReserva(){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setMessage("La reserva de "+alojamiento.getTitulo()+" fue realizada con éxito")
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        reservaViewModel.getReservaRealizada().removeObservers(requireActivity());
                        reservaViewModel.reservaTerminada();
                        while(navController.navigateUp()){}
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
        showingLastDialog = true;
    }
}