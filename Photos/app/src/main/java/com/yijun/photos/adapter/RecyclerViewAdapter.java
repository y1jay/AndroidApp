package com.yijun.photos.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.yijun.photos.MainActivity;
import com.yijun.photos.Photo;
import com.yijun.photos.R;
import com.yijun.photos.data.Photos;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Context context;
    ArrayList<Photos> photosArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Photos> photosArrayList){
        this.context = context;
        this.photosArrayList = photosArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 첫번째 파라미터인, parent로 부터 뷰(화면 : 하나의 셀)를 생성한다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_row, parent, false); //inflate=만들라는 뜻
        //리턴에, 위에서 생성한 뷰를, 뷰홀더에 담아서 리턴한다.
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // 어레이리스트에 저장된 데이터를 화면과 연결 : bind
        Photos photos= photosArrayList.get(position);
        String Title = photos.getTitle();
        int userId = photos.getId();
        int albumId = photos.getAlbumId();
//        String url = photos.getUrl();
        String thumnail = photos.getThumnailUrl();
        holder.txtTitle.setText(Title);
        holder.txtuserId.setText("userId : "+userId);
        holder.txtalbumId.setText(""+albumId);
        GlideUrl glideurl = new GlideUrl(thumnail, new LazyHeaders.Builder().addHeader("User-Agent","Your-user-agent").build());
        Glide.with(context).load(glideurl).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return photosArrayList .size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle;
        public TextView txtuserId;
        public TextView txtalbumId;
        public ImageView img;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtuserId = itemView.findViewById(R.id.txtuserId);
            txtalbumId = itemView.findViewById(R.id.txtalbumId);
            img = itemView.findViewById(R.id.img);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Photos photo = photosArrayList.get(getAdapterPosition());
                    Intent i = new Intent(context, Photo.class);
                    i.putExtra("photos",photo);
                    context.startActivity(i);

                }
            });
        }
    }
}
