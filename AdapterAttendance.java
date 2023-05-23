package com.example.report;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdapterAttendance extends FirebaseRecyclerAdapter<Attendance,AdapterAttendance.MyViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterAttendance(@NonNull FirebaseRecyclerOptions<Attendance> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Attendance model) {
        holder.txtsub.setText(model.getSubj());
        holder.txtdep.setText(model.getDept());
        holder.txtLoc.setText(model.getLocate());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtname,txtsub,txtdep,txtLoc;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            txtname = itemView.findViewById(R.id.txtAttenName);
            txtsub = itemView.findViewById(R.id.txtAttenSub);
            txtdep = itemView.findViewById(R.id.txtAttenDep);
            txtLoc = itemView.findViewById(R.id.txtAttenLoc);
        }
    }

}
