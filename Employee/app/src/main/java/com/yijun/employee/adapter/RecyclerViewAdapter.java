package com.yijun.employee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.yijun.employee.R;
import com.yijun.employee.data.Employee;

import java.util.ArrayList;
//2.상속
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    // 3.멤버변수 선언
    Context context;
    ArrayList<Employee> empList;

    // 4. 생성자 만들기
    public RecyclerViewAdapter(Context context, ArrayList<Employee> empList) {
        this.context = context;
        this.empList = empList;
    }
    // 5. 오버라이딩함수 구현
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 첫번째 파라미터인, parent로 부터 뷰(화면 : 하나의 셀)를 생성한다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_row, parent, false); //inflate=만들라는 뜻
        //리턴에, 위에서 생성한 뷰를, 뷰홀더에 담아서 리턴한다.
        return new ViewHolder(view);
    }

    // 6. RecyclerViewAdapter.ViewHolder holder 로 파라미터 변경
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // 어레이리스트에 저장된 데이터를 화면과 연결 : bind
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
// 1. 뷰 홀더 클래스 먼저 만든다.
    public class ViewHolder extends RecyclerView.ViewHolder {
        // 1. 멤버변수
        public TextView txtName;
        public TextView txtAge;
        public TextView txtSalary;
        // 카드뷰 클릭하면 화면 넘어갈 수 있도록, 멤버 변수 셋팅


        public ViewHolder(View itemView) {
            super(itemView);

            // 2. 생성자 안에서, 멤버변수 연결
            txtName = itemView.findViewById(R.id.txtName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtSalary = itemView.findViewById(R.id.txtSalary);

        }
    }
}
