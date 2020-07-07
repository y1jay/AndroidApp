package com.test.nearbysearch.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.test.nearbysearch.MapsActivity;
import com.test.nearbysearch.R;
import com.test.nearbysearch.place.Store;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Context context;
    ArrayList<Store> storeArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Store> storeArrayList){
        this.context = context;
        this.storeArrayList = storeArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 첫번째 파라미터인, parent로 부터 뷰(화면 : 하나의 셀)를 생성한다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store, parent, false); //inflate=만들라는 뜻
        //리턴에, 위에서 생성한 뷰를, 뷰홀더에 담아서 리턴한다.
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // 어레이리스트에 저장된 데이터를 화면과 연결 : bind
        Store store= storeArrayList.get(position);
        String name = store.getName();
       String vicinity = store.getVicinity();

        holder.txtName.setText(name);
        holder.txtType.setText(vicinity);

//        GlideUrl glideurl = new GlideUrl(url, new LazyHeaders.Builder().addHeader("User-Agent","Your-user-agent").build());
//        Glide.with(context).load(glideurl).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return storeArrayList .size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtName;
        public TextView txtType;

        public CardView cardView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
           txtName = itemView.findViewById(R.id.txtName);
            txtType = itemView.findViewById(R.id.txtType);

            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  Store store = storeArrayList.get(getAdapterPosition());
                  Intent i = new Intent(context, MapsActivity.class);
                   i.putExtra("store",store);
                    context.startActivity(i);
                }
            });
        }
    }
}
