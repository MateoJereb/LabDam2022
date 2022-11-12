package com.mdgz.dam.labdam2022.recycler_views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.databinding.LineaLogBinding;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {

    private List<String> lineas;
    private LayoutInflater inflater;
    private Context context;

    public LogAdapter(List<String> lineas, Context context){
     this.lineas = lineas;
     this.context = context;
     inflater = LayoutInflater.from(context);
    }

    public void setLineas(List<String> lineas){
        this.lineas = lineas;
    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.linea_log,parent,false);
        return new LogAdapter.LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
        holder.bindData(lineas.get(position));
    }

    @Override
    public int getItemCount() {
        return lineas.size();
    }

    public static class LogViewHolder extends RecyclerView.ViewHolder{
        TextView linea;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            linea = itemView.findViewById(R.id.lineaTextView);
        }

        public void bindData(String text){
            linea.setText(text);
        }
    }
}
