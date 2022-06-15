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
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdapterFailure extends RecyclerView.Adapter<AdapterFailure.failureHolder> {

    private Context context;
    private List<FailureModel> failureModelList;

    public AdapterFailure(Context context, List<FailureModel> failureModelList) {
        this.context = context;
        this.failureModelList = failureModelList;
    }


    @NonNull
    @Override
    public failureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.failure_template, parent, false);
        return new failureHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull failureHolder holder, int position) {

        FailureModel failureModel = failureModelList.get(position);
        String failureImage = failureModelList.get(position).getFailure_image();
        String failureKey = failureModelList.get(position).getKey();
        String failureAbstract = failureModelList.get(position).getFailure_abstract();
        List<String> solutions = failureModelList.get(position).getSolutions();
        solutions.remove(null);

        holder.failureKey.setText(failureKey);
        holder.failureAbstract.setText(failureAbstract);
        holder.failureSolutionsCount.setText(""+solutions.size());



        try {
            Picasso.get().load(failureImage).placeholder(R.drawable.system_logo).into(holder.failureImage);
        }catch (Exception e){}

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent failureDescriptionNSoltionsIntent = new Intent(context, failure_description_n_solutions.class);
                failureDescriptionNSoltionsIntent.putExtra("f_ID", failureModel.getFailure_id());
                context.startActivity(failureDescriptionNSoltionsIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return failureModelList.size();
    }

    class failureHolder extends RecyclerView.ViewHolder{
        private ImageView failureImage;
        private TextView failureKey, failureAbstract,failureSolutionsCount;
        public failureHolder(@NonNull View itemView) {
            super(itemView);

            failureImage = itemView.findViewById(R.id.failure_image);
            failureKey = itemView.findViewById(R.id.failure_key);
            failureAbstract = itemView.findViewById(R.id.failure_abstract);
            failureSolutionsCount = itemView.findViewById(R.id.failure_solutions_count);

        }
    }
}
