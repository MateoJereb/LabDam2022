package com.mdgz.dam.labdam2022;

import android.opengl.Visibility;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.mdgz.dam.labdam2022.viewmodels.LogViewModel;

public class SettingsFragment extends PreferenceFragmentCompat {

    private NavController navController;
    private LogViewModel viewModel;

    private ListPreference metodoPagoList;
    private ListPreference monedaList;
    private Preference logPreference;
    private SwitchPreferenceCompat guardarInfoSwitch;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(LogViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        navController = NavHostFragment.findNavController(this);
        container.getContext().setTheme(R.style.PreferencesScreen);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        metodoPagoList = findPreference("metodo_pago");
        monedaList = findPreference("moneda");
        guardarInfoSwitch = findPreference("info_uso");
        logPreference = findPreference("log");

        String metodoSelected = metodoPagoList.getValue();
        monedaList.setEnabled(metodoPagoList.findIndexOfValue(metodoSelected) == 0);

        metodoPagoList.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int pos = metodoPagoList.findIndexOfValue(newValue.toString());
                monedaList.setEnabled(pos == 0);

                return true;
            }
        });

        guardarInfoSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Boolean permitido = (Boolean) newValue;

                if(!permitido){
                    Boolean borrado = viewModel.deleteLogBusquedas();
                    Toast.makeText(requireActivity(),"Información de uso eliminada",Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

        logPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                navController.navigate(R.id.action_settingsFragmento_to_logFragment);
                return true;
            }
        });
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}