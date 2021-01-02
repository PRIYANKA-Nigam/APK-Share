package com.example.apkshare;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {
Context context;
    List<App> apps;

    public AppAdapter(Context context, List<App> apps) {
        this.context = context;
        this.apps = apps;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.app_row,parent,false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
     holder.name.setText(apps.get(position).getName());
     long appSize =apps.get(position).getAppSize();holder.size.setText(getReadableSize(appSize));
     holder.appIcon.setImageDrawable(apps.get(position).getIcon());
     holder.cardView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent=new Intent();
             intent.setAction(Intent.ACTION_SEND);
             intent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(context,BuildConfig.APPLICATION_ID + ".provider",new File(apps.get(position).getAppPath())));
             intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
             intent.setType("application/vnd.android.package-archive");
             context.startActivity(Intent.createChooser(intent,"Share APK"));
         }
     });

    }

    private String getReadableSize(long appSize) {
        String readable;
        if (appSize<1024){
            readable=String.format(context.getString(R.string.app_size_b),(double)appSize);
        }else if (appSize<Math.pow(1024,2)){
            readable=String.format(context.getString(R.string.app_size_k),(double)(appSize/1024));
        }else if (appSize<Math.pow(1024,3)){
            readable=String.format(context.getString(R.string.app_size_b),(double)(appSize)/Math.pow(1024,2));
        }else {
            readable=String.format(context.getString(R.string.app_size_b),(double)(appSize/Math.pow(1024,3)));
        }
        return readable;
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder{
       CardView cardView;
        ImageView appIcon;
        TextView name,size;
        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.row);
            appIcon=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            size=itemView.findViewById(R.id.size);
        }
    }
}
