package com.example.noviprojekat;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Measurement> mDataset;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTemp;
        TextView textViewHumid;
        TextView textViewaq;
        TextView textViewts;

        // each data item is just a string in this case
        public MyViewHolder(View v) {
            super(v);
            textViewTemp = v.findViewById(R.id.temperature);
            textViewHumid = v.findViewById(R.id.humidity);
            textViewaq = v.findViewById(R.id.pollution);
            textViewts = v.findViewById(R.id.timestamp);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Measurement> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_history_row, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
           holder.textViewTemp.setText(mDataset.get(position).getTemperature());
           holder.textViewHumid.setText(mDataset.get(position).getHumidity());
           holder.textViewts.setText(mDataset.get(position).getTimestamp());
           holder.textViewaq.setText(mDataset.get(position).getPollution());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
