package com.yijun.todos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yijun.todos.R;
import com.yijun.todos.data.Todos;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Context context;
    ArrayList<Todos> todosArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Todos> todosArrayList){
        this.context = context;
        this.todosArrayList = todosArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 첫번째 파라미터인, parent로 부터 뷰(화면 : 하나의 셀)를 생성한다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todos_row, parent, false); //inflate=만들라는 뜻
        //리턴에, 위에서 생성한 뷰를, 뷰홀더에 담아서 리턴한다.
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // 어레이리스트에 저장된 데이터를 화면과 연결 : bind
        Todos todos= todosArrayList.get(position);
        String Title = todos.getTitle();
        int userId = todos.getUserId();
        boolean completed = todos.isCompleted();
        holder.txtTitle.setText(Title);
       holder.txtUserId.setText("userId : "+userId);

       if(completed == true){
           holder.txtCompleted.setText("완료");
       }else{
           holder.txtCompleted.setText("실패");
       }

    }

    @Override
    public int getItemCount() {
        return todosArrayList .size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle;
        public TextView txtUserId;
        public TextView txtCompleted;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtUserId = itemView.findViewById(R.id.txtUserId);
            txtCompleted = itemView.findViewById(R.id.txtCompleted);
        }
    }
}
