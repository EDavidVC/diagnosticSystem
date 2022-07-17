package com.davidvelz.diagnosticsystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.davidvelz.diagnosticsystem.objects.FailureModel;
import com.davidvelz.diagnosticsystem.*;
import com.davidvelz.diagnosticsystem.objects.SolutionModel;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdapterSolution extends RecyclerView.Adapter<AdapterSolution.solutionHolder> {

    private Context context;
    private List<SolutionModel> solutionModelList;

    public AdapterSolution(Context context, List<SolutionModel> solutionModelList) {
        this.context = context;
        this.solutionModelList = solutionModelList;
    }


    @NonNull
    @Override
    public solutionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.solution_template, parent, false);
        return new solutionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull solutionHolder holder, int position) {

        String solutionKey = solutionModelList.get(position).getKey();
        double solutionPrice = solutionModelList.get(position).getPrice();
        String solutionID = solutionModelList.get(position).getSolution_id();

        String idpluskey = position+1+".- "+solutionKey;
        holder.solution_key.setText(idpluskey);
        holder.solution_price.setText(""+solutionPrice);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent solutionDescription = new Intent(context, solution_description.class);
                solutionDescription.putExtra("solution_id", solutionID);
                context.startActivity(solutionDescription);
            }
        });

    }

    @Override
    public int getItemCount() {
        return solutionModelList.size();
    }

    class solutionHolder extends RecyclerView.ViewHolder{
        private TextView solution_key, solution_price;
        public solutionHolder(@NonNull View itemView) {
            super(itemView);

            solution_key = itemView.findViewById(R.id.solution_key);
            solution_price = itemView.findViewById(R.id.solution_price_appr);

        }
    }
}
