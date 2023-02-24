package com.mdgz.dam.labdam2022.recycler_views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.ui.ResultadoBusquedaFragment;
import com.mdgz.dam.labdam2022.viewmodels.BusquedaViewModel;

import java.util.List;

public class AlojamientosAdapter extends RecyclerView.Adapter<AlojamientosAdapter.AlojamientosViewHolder>{

    private List<Alojamiento> listaAlojamientos;
    private LayoutInflater inflater;
    private Context context;
    private AlojamientosAdapter.OnItemClickListener listener;
    private AlojamientosAdapter.OnCheckedChangedListener favListener;

    public AlojamientosAdapter(List<Alojamiento> lista,Context context){
        listaAlojamientos = lista;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setListaAlojamientos(List<Alojamiento> listaAlojamientos) {
        this.listaAlojamientos = listaAlojamientos;
    }

    public void setOnItemClickListener(AlojamientosAdapter.OnItemClickListener listener){
        this.listener = listener;
    }

    public void setOnCheckedChangedListener(AlojamientosAdapter.OnCheckedChangedListener favListener){
        this.favListener = favListener;
    }

    @NonNull
    @Override
    public AlojamientosAdapter.AlojamientosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fila_resultado,parent,false);
        return new AlojamientosAdapter.AlojamientosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlojamientosAdapter.AlojamientosViewHolder holder, int position) {
        holder.bindData(listaAlojamientos.get(position),listener,favListener);
    }

    @Override
    public int getItemCount() {
        return listaAlojamientos.size();
    }

    public interface OnItemClickListener{
        void onItemClick(Alojamiento item);
    }

    public interface OnCheckedChangedListener{
        void onCheckedChanged(Alojamiento item, Boolean newValue);
    }

    public static class AlojamientosViewHolder extends RecyclerView.ViewHolder{
        Alojamiento aloj;
        TextView titulo;
        TextView descripcion;
        CheckBox favorito;
        TextView precio;
        ImageView imagen;

        AlojamientosViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tituloTextView);
            descripcion = itemView.findViewById(R.id.descripcionTextView);
            favorito = itemView.findViewById(R.id.favoritoCheckBox);
            precio = itemView.findViewById(R.id.precioTextView);
            imagen = itemView.findViewById(R.id.imageView);
        }

        public Alojamiento getAloj() {
            return aloj;
        }

        void bindData(Alojamiento alojamiento, AlojamientosAdapter.OnItemClickListener listener, AlojamientosAdapter.OnCheckedChangedListener favListener){
            titulo.setText(alojamiento.getTitulo());
            descripcion.setText(alojamiento.getDescripcion());
            favorito.setChecked(alojamiento.getFavorito());

            String precioBase = "$"+String.format("%.2f",alojamiento.getPrecioBase());
            precio.setText(precioBase);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    listener.onItemClick(alojamiento);
                }
            });
            imagen.setImageResource(alojamiento.getImagen());

            favorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean newValue) {
                    favListener.onCheckedChanged(alojamiento,newValue);
                }
            });
        }
    }
}
