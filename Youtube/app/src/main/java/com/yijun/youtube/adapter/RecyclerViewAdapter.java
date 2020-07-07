package com.yijun.youtube.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.yijun.youtube.Photo;
import com.yijun.youtube.R;
import com.yijun.youtube.Video;
import com.yijun.youtube.data.Youtube;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Context context;
    ArrayList<Youtube> youtubeArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Youtube> youtubeArrayList){
        this.context = context;
        this.youtubeArrayList = youtubeArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 첫번째 파라미터인, parent로 부터 뷰(화면 : 하나의 셀)를 생성한다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.youtube_row, parent, false); //inflate=만들라는 뜻
        //리턴에, 위에서 생성한 뷰를, 뷰홀더에 담아서 리턴한다.
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // 어레이리스트에 저장된 데이터를 화면과 연결 : bind
        Youtube youtube= youtubeArrayList.get(position);
        String title = youtube.getTitle();
        String url = youtube.getUrl();
        String description = youtube.getDescription();
        holder.txtTitle.setText(title);
        holder.txtdesc.setText(description);

        GlideUrl glideurl = new GlideUrl(url, new LazyHeaders.Builder().addHeader("User-Agent","Your-user-agent").build());
        Glide.with(context).load(glideurl).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return youtubeArrayList .size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle;
        public TextView txtdesc;
        public ImageView img;
        public CardView cardView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtdesc = itemView.findViewById(R.id.txtdesc);
            img = itemView.findViewById(R.id.img);
            cardView = itemView.findViewById(R.id.cardView);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Youtube youtube = youtubeArrayList.get(getAdapterPosition());
                   Intent i = new Intent(context, Photo.class);
                  i.putExtra("thumbnail",youtube);
                    context.startActivity(i);



                }
            });
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Youtube youtube = youtubeArrayList.get(getAdapterPosition());
                    Intent i = new Intent(context, Video.class);
                    i.putExtra("youtube",youtube);
                    context.startActivity(i);
                }
            });
        }
    }
}
