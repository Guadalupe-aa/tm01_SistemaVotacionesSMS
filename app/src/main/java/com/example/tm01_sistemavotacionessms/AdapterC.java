package com.example.tm01_sistemavotacionessms;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterC extends RecyclerView.Adapter<AdapterC.MyViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private List<Competitor> listC;
    View.OnClickListener cl;
    View.OnLongClickListener lcl;

    public AdapterC(List<Competitor> listC) {
        this.listC = listC;
    }

    public void setListC(List<Competitor> listC) {
        this.listC = listC;
    }

    @NonNull
    @Override
    public AdapterC.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowC = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_competitor, parent, false);
        rowC.setOnClickListener(this);
        rowC.setOnLongClickListener(this);
        return new MyViewHolder(rowC);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull AdapterC.MyViewHolder holder, int position) {
        Competitor comp = listC.get(position);

        holder.getIdI().setText("Id: " + comp.getId());
        holder.getNameI().setText("Name: "  + comp.getName());
        holder.getSurnameI().setText(comp.getSurname());
        holder.getNicknameI().setText("Nickname: " + comp.getNickname());
        holder.getVotesI().setText("Votes: " + String.valueOf(comp.getVotes()));
        holder.getIm().setImageResource(R.mipmap.icc_launcher);

    }

    @Override
    public int getItemCount() {
        return listC.size();
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.cl = listener;
    }

    public void setOnLongClickListener(View.OnLongClickListener longClickListener){
        this.lcl = longClickListener;
    }

    @Override
    public void onClick(View v) {
        if(cl != null){
            cl.onClick(v);
        }

    }

    @Override
    public boolean onLongClick(View v) {
        if(lcl != null){
             lcl.onLongClick(v);
        }
       // return true;
        return true;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView idI,  nameI, nicknameI, surnameI, votesI;
        private ImageView im;

        MyViewHolder(View itemView) {
            super(itemView);
            this.idI = itemView.findViewById(R.id.tvId_row);
            this.nameI = itemView.findViewById(R.id.tvName_row);
            this.surnameI = itemView.findViewById(R.id.tvsurname_row);
            this.nicknameI = itemView.findViewById(R.id.tvnickname_row);
            this.votesI = itemView.findViewById(R.id.tvVotes_row);
            this.im = itemView.findViewById(R.id.imageView_row);
        }

        public TextView getIdI() {
            return idI;
        }

        public void setIdI(TextView idI) {
            this.idI = idI;
        }

        public TextView getNameI() {
            return nameI;
        }

        public void setNameI(TextView nameI) {
            this.nameI = nameI;
        }

        public TextView getNicknameI() {
            return nicknameI;
        }

        public void setNicknameI(TextView nicknameI) {
            this.nicknameI = nicknameI;
        }

        public TextView getSurnameI() {
            return surnameI;
        }

        public void setSurnameI(TextView surnameI) {
            this.surnameI = surnameI;
        }

        public TextView getVotesI() {
            return votesI;
        }

        public void setVotesI(TextView votesI) {
            this.votesI = votesI;
        }

        public ImageView getIm() {
            return im;
        }

        public void setIm(ImageView im) {
            this.im = im;
        }
    }
}
