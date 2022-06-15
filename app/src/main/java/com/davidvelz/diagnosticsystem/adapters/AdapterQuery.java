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

import com.davidvelz.diagnosticsystem.objects.QueryModel;
import com.davidvelz.diagnosticsystem.objects.SystemModel;
import com.davidvelz.diagnosticsystem.*;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterQuery extends RecyclerView.Adapter<AdapterQuery.queryHolder> {

    private Context context;
    private List<QueryModel> queryModelList;

    public AdapterQuery(Context context, List<QueryModel> queryModelList) {
        this.context = context;
        this.queryModelList = queryModelList;
    }


    @NonNull
    @Override
    public queryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.query_template, parent, false);
        return new queryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull queryHolder holder, int position) {
        String queryKey = queryModelList.get(position).getKey();
        String queryDetails  = queryModelList.get(position).getDetails();

        holder.queryKey.setText(queryKey);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent queryDetailsViewIntent = new Intent(context, query_details_view.class);
                queryDetailsViewIntent.putExtra("key", queryKey);
                queryDetailsViewIntent.putExtra("details", queryDetails);
                context.startActivity(queryDetailsViewIntent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return queryModelList.size();
    }

    class queryHolder extends RecyclerView.ViewHolder{
        private TextView queryKey;
        public queryHolder(@NonNull View itemView) {
            super(itemView);

            queryKey = itemView.findViewById(R.id.query_key);

        }
    }
}

