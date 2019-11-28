/*
Sources used :
https://developer.android.com/guide/topics/ui/layout/recyclerview
https://codinginflow.com/tutorials/android/recyclerview-cardview/part-1-layouts-model-class
 */

package be.mikedhoore.realstation.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.mikedhoore.realstation.Models.Station;
import be.mikedhoore.realstation.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private ArrayList<Station> arrayListOfStations;
    private OnItemClickListener onItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public ListAdapter(ArrayList<Station> stationArrayList){
        arrayListOfStations = stationArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Station current = arrayListOfStations.get(position);
        holder.textView.setText(current.getName());
    }

    @Override
    public int getItemCount() {
        return arrayListOfStations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.list_item_Title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
