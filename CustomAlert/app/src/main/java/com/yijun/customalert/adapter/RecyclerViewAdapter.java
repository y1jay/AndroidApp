package com.yijun.customalert.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.yijun.customalert.R;
import com.yijun.customalert.data.CustomAlert;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    // 멤버변수 셋팅
    Context context;
    ArrayList<CustomAlert> customAlertArrayList;

    // 1. 생성자 만들기
    public RecyclerViewAdapter(Context context, ArrayList<CustomAlert> customAlertArrayList){
        this.context = context;
        this.customAlertArrayList = customAlertArrayList;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 첫번째 파라미터인, parent로 부터 뷰(화면 : 하나의 셀)를 생성한다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_row,parent,false); //inflate=만들라는 뜻
        //리턴에, 위에서 생성한 뷰를, 뷰홀더에 담아서 리턴한다.
        return new ViewHolder(view);
    }

    //리스트에 있는 데이터(주소록데이터)를, 화면에 있는 뷰(텍스트뷰, 이미지뷰...)에 표시하는 메소드
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // 1. 해당 포지션의 데이터 가져와서
        CustomAlert customAlert = customAlertArrayList.get(position);
        String title = customAlert.getTitle();
        String body = customAlert.getBody();
        // 2. 뷰홀더에 있는 텍스트뷰에 문자열을 셋팅한다.
        holder.txtTitle.setText(title);
        holder.txtBody.setText(body);

    }

    //리스트에 있는 데이터의 객수를 리턴해줘야 한다.
    @Override
    public int getItemCount() {
        return customAlertArrayList.size();
    }


    // 하나의 셀 (xml) 화면에 있는 구성요소(텍스트뷰, 이미지뷰 ...)를 여기서 연결한다.
    public class ViewHolder extends RecyclerView.ViewHolder{
        // 1. 멤버변수
        public TextView txtTitle;
        public TextView txtBody;
        public ImageView imgDelete;
        // 카드뷰 클릭하면 화면 넘어갈 수 있도록, 멤버 변수 셋팅
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // 2. 생성자 안에서, 멤버변수 연결
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtBody= itemView.findViewById(R.id.txtBody);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            cardView = itemView.findViewById(R.id.cardView);




            //X버튼 처리
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder deleteAlert = new AlertDialog.Builder(context);
                    deleteAlert.setTitle("삭제");
                    deleteAlert.setMessage("정말 삭제하시겠습니까?");
                    deleteAlert.setPositiveButton("yes.", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            customAlertArrayList.remove(getAdapterPosition());
                            notifyDataSetChanged();
                            //notifyItemchanged(index)라는 함수도 있다.
                        }
                    });
                    deleteAlert.setNegativeButton("No", null);
                    deleteAlert.setCancelable(false);
                    deleteAlert.show();
                    return;

                }
            });

        }
    }

}