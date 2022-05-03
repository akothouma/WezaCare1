package com.example.wezacare1;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Cruiseshipadapter extends RecyclerView.Adapter<Cruiseshipadapter.Cruiseshipviewholder>{

    List<CruiseShipsModel> cruiseShipsModelList;
    Context context;

    OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick( int pos);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener= listener;
    }


    public Cruiseshipadapter(List<CruiseShipsModel> cruiseShipsModelList, Context context) {
        this.cruiseShipsModelList = cruiseShipsModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public Cruiseshipadapter.Cruiseshipviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cruiseshipviewholder,parent,false);
        return new Cruiseshipviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Cruiseshipadapter.Cruiseshipviewholder holder, int position) {
        CruiseShipsModel current= cruiseShipsModelList.get(position);
        Picasso.get().load(current.getImage()).placeholder(R.drawable.img).fit().purgeable().centerCrop().into(holder.imageshowcase);
        holder.t1.setText(current.getShip_name());
//
    }

    @Override
    public int getItemCount() {
        return cruiseShipsModelList.size();
    }

    public interface OnItemClickListiner {
        void onItemClick(int pos);
    }


    public class Cruiseshipviewholder extends RecyclerView.ViewHolder {

        ImageView imageshowcase;
        TextView t1;

        public Cruiseshipviewholder(@NonNull View itemView) {
            super(itemView);

            imageshowcase=itemView.findViewById(R.id.imageViewofship);
            t1=itemView.findViewById(R.id.nameofship);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int pos = getAdapterPosition();
                        if (pos != Adapter.NO_SELECTION) {
                            listener.onItemClick(pos);
                        }
                    }
                }
        });
    }
    }
    }


