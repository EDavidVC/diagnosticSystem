package com.davidvelz.diagnosticsystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.davidvelz.diagnosticsystem.objects.SystemModel;
import com.davidvelz.diagnosticsystem.*;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterSystem extends RecyclerView.Adapter<AdapterSystem.systemHolder> {

    private Context context;
    private List<SystemModel> systemModelList;

    public AdapterSystem(Context context, List<SystemModel> systemModelList) {
        this.context = context;
        this.systemModelList = systemModelList;
    }


    @NonNull
    @Override
    public systemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.system_template, parent, false);
        return new systemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull systemHolder holder, int position) {
        String systemLogo = systemModelList.get(position).getIcon();
        String serviceName = systemModelList.get(position).getName_ES();
        String serviceDetails = systemModelList.get(position).getDescription();
        String serviceID = systemModelList.get(position).getSystem_id();

        holder.systemName.setText(serviceName);
        holder.systemDetails.setText(serviceDetails);

        try {
            Picasso.get().load(systemLogo).placeholder(R.drawable.system_logo).into(holder.systemLogo);
        }catch (Exception e){}

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent failIntent = new Intent(context, failure_list.class);
                failIntent.putExtra("system_id",serviceID);
                failIntent.putExtra("name_ES", serviceName);
                context.startActivity(failIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return systemModelList.size();
    }

    class systemHolder extends RecyclerView.ViewHolder{
        private ImageView systemLogo;
        private TextView systemName, systemDetails;
        public systemHolder(@NonNull View itemView) {
            super(itemView);

            systemLogo = itemView.findViewById(R.id.system_logo);
            systemName = itemView.findViewById(R.id.system_name);
            systemDetails = itemView.findViewById(R.id.system_details);

        }
    }
}
