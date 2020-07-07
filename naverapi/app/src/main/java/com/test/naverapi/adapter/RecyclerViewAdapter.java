package com.test.naverapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.test.naverapi.MainActivity;
import com.test.naverapi.R;
import com.test.naverapi.data.Model;


import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    // 멤버변수 셋팅
    Context context;
    ArrayList<Model> modelArrayList;

    // 1. 생성자 만들기
    public RecyclerViewAdapter(Context context, ArrayList<Model> modelArrayList){
        this.context = context;
        this.modelArrayList = modelArrayList;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 첫번째 파라미터인, parent로 부터 뷰(화면 : 하나의 셀)를 생성한다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trans_row,parent,false); //inflate=만들라는 뜻
        //리턴에, 위에서 생성한 뷰를, 뷰홀더에 담아서 리턴한다.
        return new ViewHolder(view);
    }

    //리스트에 있는 데이터(주소록데이터)를, 화면에 있는 뷰(텍스트뷰, 이미지뷰...)에 표시하는 메소드
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // 1. 해당 포지션의 데이터 가져와서
        Model model = modelArrayList.get(position);
        String trans = model.getResult();

        // 2. 뷰홀더에 있는 텍스트뷰에 문자열을 셋팅한다.
        holder.txttrans.setText(trans);


    }

    //리스트에 있는 데이터의 객수를 리턴해줘야 한다.
    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }


    // 하나의 셀 (xml) 화면에 있는 구성요소(텍스트뷰, 이미지뷰 ...)를 여기서 연결한다.
    public class ViewHolder extends RecyclerView.ViewHolder{
        // 1. 멤버변수
        public TextView txttrans;

        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // 2. 생성자 안에서, 멤버변수 연결
            txttrans = itemView.findViewById(R.id.txttrans);
            cardView = itemView.findViewById(R.id.cardView);



        }
    }

}
