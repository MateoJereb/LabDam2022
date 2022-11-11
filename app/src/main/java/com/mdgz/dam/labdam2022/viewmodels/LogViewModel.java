package com.mdgz.dam.labdam2022.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LogViewModel extends AndroidViewModel {

    public static String nombreFile = "log_busquedas.txt";

    public LogViewModel(@NonNull Application application) {
        super(application);
    }

    public List<String> readLogBusquedas(){
        List<String> salida = new ArrayList<>();

        FileInputStream fileInput;

        try{
            fileInput = getApplication().openFileInput(nombreFile);
        }
        catch (FileNotFoundException e){
            salida.add("Realice alguna búsqueda primero");
            return salida;
        }

        InputStreamReader inputStreamReader = new InputStreamReader(fileInput, StandardCharsets.UTF_8);

        try(BufferedReader reader = new BufferedReader(inputStreamReader)){
            String line = reader.readLine();

            while(line != null){
                salida.add(line);
                line = reader.readLine();
            }

            fileInput.close();
        }
        catch (IOException e){ e.printStackTrace(); }

        return salida;
    }

    public Boolean deleteLogBusquedas(){
        return getApplication().deleteFile(nombreFile);
    }

    public void writeLogBusquedas(String log){
        try(FileOutputStream fileOutput = getApplication().openFileOutput(nombreFile, Context.MODE_APPEND)){
            fileOutput.write(log.getBytes(StandardCharsets.UTF_8));
            fileOutput.write("\n".getBytes(StandardCharsets.UTF_8));
            fileOutput.flush();
            fileOutput.close();
        }
        catch (IOException e){ e.printStackTrace();}
    }
}
