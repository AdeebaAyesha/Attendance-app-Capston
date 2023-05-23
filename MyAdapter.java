package com.example.report;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class MyAdapter  extends FirebaseRecyclerAdapter<Users,MyAdapter.myViewHolder>{


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyAdapter(@NonNull FirebaseRecyclerOptions<Users> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Users model) {

        holder.id.setText(model.getId());
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.password.setText(model.getPassword());
        holder.phone_number.setText(model.getPhone_number());

        //Update User Data
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogPlus dialogPlus = DialogPlus.newDialog(holder.id.getContext()).
                        setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new ViewHolder(R.layout.edit))
                        .setExpanded(false)
                        .create();
                View holderView = (LinearLayout) dialogPlus.getHolderView();

                EditText updateNames = holderView.findViewById(R.id.updateNames);
                EditText updateUserName = holderView.findViewById(R.id.updateUserName);
                EditText updatePassword = holderView.findViewById(R.id.updatePassword);
                EditText updateId= holderView.findViewById(R.id.updateId);
                EditText updatePhone= holderView.findViewById(R.id.updatePhone);

                updateNames.setText(model.getName());
                updateUserName.setText(model.getEmail());
                updatePassword.setText(model.getPassword());
                updateId.setText(model.getId());
                updatePhone.setText(model.getPhone_number());

                Button update_butn = holderView.findViewById(R.id.update_butn);
                update_butn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("name",updateNames.getText().toString());
                        map.put("email",updateUserName.getText().toString());
                        map.put("password",updatePassword.getText().toString());
                        map.put("id",updateId.getText().toString());
                        map.put("phone_number",updatePhone.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Users").child("Teacher")
                                .child(getRef(position).getKey())
                                .updateChildren(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
                dialogPlus.show();
            }
        });

        // Delete User Data
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.id.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Users").child("Teacher")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView id,name,email,password,phone_number;

        ImageView edit,delete;
        public myViewHolder(@NonNull View itemView){
            super(itemView);

            id = itemView.findViewById(R.id.txtid);
            name = itemView.findViewById(R.id.txtname);
            email = itemView.findViewById(R.id.txtEmail);
            password = itemView.findViewById(R.id.txtpassword);
            phone_number = itemView.findViewById(R.id.txtphoneNo);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
// RecyclerView.Adapter<MyAdapter.MyViewHolder> {
//
//    Context context;
//    ArrayList<Users> list;
//
//    public MyAdapter(Context context, ArrayList<Users> list) {
//        this.context = context;
//        this.list = list;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
//        return new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Users users = list.get(position);
//        holder.id.setText(users.getId());
//        holder.name.setText(users.getName());
//        holder.password.setText(users.getPassword());
//        holder.email.setText(users.getEmail());
//        holder.phone_number.setText(users.getPhone_number());
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//
//        TextView id,name, password,email,phone_number;
//
//        public MyViewHolder(@NonNull View itemView){
//            super(itemView);
//
//            id = itemView.findViewById(R.id.id);
//            name = itemView.findViewById(R.id.name);
//            password = itemView.findViewById(R.id.password);
//            email = itemView.findViewById(R.id.username);
//            phone_number = itemView.findViewById(R.id.phoneNo);
//
//        }
//    }
//
//}
//
