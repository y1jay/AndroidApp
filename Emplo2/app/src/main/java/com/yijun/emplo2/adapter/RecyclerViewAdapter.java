package com.yijun.emplo2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yijun.emplo2.R;
import com.yijun.emplo2.list.Employee;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Employee> empList;


    public RecyclerViewAdapter(Context context, ArrayList<Employee> empList) {
        this.context = context;
        this.empList = empList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        Employee employee = empList.get(position);
        String name = employee.getName();
        int age = employee.getAge();
        int salary = employee.getSalary();
        holder.txtName.setText(name);
        holder.txtAge.setText("나이 : "+age+" 세");
        holder.txtSalary.setText("연봉 : $ "+salary);


    }

    @Override
    public int getItemCount() {
        return empList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName;
        public TextView txtAge;
        public TextView txtSalary;
        public ImageView imageView;



        public ViewHolder(View itemView) {
            super(itemView);
       txtName = itemView.findViewById(R.id.txtName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtSalary = itemView.findViewById(R.id.txtSalary);
            imageView = itemView.findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.avata);
        }
    }
}
