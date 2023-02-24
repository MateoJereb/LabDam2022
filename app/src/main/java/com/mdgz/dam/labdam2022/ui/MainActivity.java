package com.mdgz.dam.labdam2022.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.window.SplashScreen;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.databinding.*;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.persistencia.room.AppDataBase;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;
import com.mdgz.dam.labdam2022.viewmodels.BusquedaViewModel;
import com.mdgz.dam.labdam2022.viewmodels.BusquedaViewModelFactory;
import com.mdgz.dam.labdam2022.viewmodels.ReservaViewModel;
import com.mdgz.dam.labdam2022.viewmodels.ReservaViewModelFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private BusquedaViewModel busquedaViewModel;
    private ReservaViewModel reservaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Toolbar toolbar = binding.materialToolbar;
        setSupportActionBar(toolbar);

        AppDataBase.getInstance(getApplicationContext());

        busquedaViewModel = new ViewModelProvider(this, new BusquedaViewModelFactory(this)).get(BusquedaViewModel.class);
        reservaViewModel = new ViewModelProvider(this, new ReservaViewModelFactory(this)).get(ReservaViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment fragmentoActual = binding.fragmentContainerView.getFragment();
        NavController navController = NavHostFragment.findNavController(fragmentoActual);

        switch ((item.getItemId())){
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.action_buscar:
                while(navController.navigateUp()){ }
                break;

            case R.id.action_config:
                if(navController.getCurrentDestination().getId() != R.id.settingsFragment && navController.getCurrentDestination().getId() != R.id.logFragment)
                    navController.navigate(R.id.to_settingsFragment);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}