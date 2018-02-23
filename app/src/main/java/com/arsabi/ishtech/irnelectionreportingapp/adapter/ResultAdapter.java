package com.arsabi.ishtech.irnelectionreportingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arsabi.ishtech.irnelectionreportingapp.R;

import java.util.List;


public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    public List<Vote> voteList;
    public Context context;

    public ResultAdapter(List<Vote> voteList, Context context) {
        this.voteList = voteList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.display,parent,false);
        return new ViewHolder(view);
    }

    public List<Vote> getList(){
        return voteList;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Vote vote = voteList.get(position);
        holder.name.setText(vote.getName());
        holder.region.setText(vote.getRegion());
        holder.district.setText(vote.getDistrict());
        holder.party.setText(vote.getParty());
        holder.constituency.setText(vote.getConst());
        holder.center_id.setText(vote.getCenter());
        holder.vote.setText(vote.getVote());
    }


    @Override
    public int getItemCount() {
        return voteList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView vote;
        public TextView region;
        public TextView district;
        public TextView constituency;
        public TextView center_id;
        public TextView party;
        public TextView station;
        public TextView ward;



        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            vote = (TextView) itemView.findViewById(R.id.vote);
            party = (TextView) itemView.findViewById(R.id.party);
            region = (TextView) itemView.findViewById(R.id.region);
            district = (TextView) itemView.findViewById(R.id.district);
            constituency = (TextView) itemView.findViewById(R.id.constituency);
            center_id = (TextView) itemView.findViewById(R.id.center);
            station = (TextView) itemView.findViewById(R.id.station);
            constituency = (TextView) itemView.findViewById(R.id.constituency);
            ward = (TextView) itemView.findViewById(R.id.ward);
        }

    }
}
