package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FrientAdapter extends RecyclerView.Adapter<FrientAdapter.Myclass> {

    Context context;
    List<FrientList> frientListList=new ArrayList<>();

    public FrientAdapter(List<FrientList> frientListList, Context mainActivity) {
        this.context=mainActivity;
        this.frientListList=frientListList;
    }


    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new Myclass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {
        FrientList recyclerData = frientListList.get(position);
        holder.firstname.setText(recyclerData.getFirstName());
        holder.lasstname.setText(recyclerData.getLastname());
        holder.imageView.setImageResource(recyclerData.getImage());
    }

    @Override
    public int getItemCount() {
        return frientListList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder
    {
        private TextView firstname,lasstname;
        private CircleImageView imageView;

        public Myclass(@NonNull View itemView) {
            super(itemView);
            firstname = itemView.findViewById(R.id.fname);
            lasstname = itemView.findViewById(R.id.lname);
            imageView = itemView.findViewById(R.id.image);
        }
    }

}
