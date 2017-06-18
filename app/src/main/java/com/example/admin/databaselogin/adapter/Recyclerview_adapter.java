package com.example.admin.databaselogin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.databaselogin.R;
import com.example.admin.databaselogin.model.student_test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 6/7/2017.
 */

public class Recyclerview_adapter extends RecyclerView.Adapter<Recyclerview_adapter.viewholder> {

    List<student_test> list = new ArrayList<>();
    Context activity;

    public Recyclerview_adapter(Context activity, List<student_test> list) {
        this.list = list;
        //this.activity = activity;
    }





    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_display_students,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(final viewholder holder, int position) {
        student_test pos = list.get(position);
        //Log.d("data1", pos(position).getName());
        holder.stu_id.setText(pos.getId());
        holder.stu_name.setText(pos.getName());
        holder.stu_address.setText(pos.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView stu_id, stu_name, stu_address;


        public viewholder(View itemView) {
            super(itemView);

            stu_id = (TextView) itemView.findViewById(R.id.txt_id);
            stu_name = (TextView) itemView.findViewById(R.id.txt_name);
            stu_address = (TextView) itemView.findViewById(R.id.txt_address);






        }
    }
}
