package be.mikedhoore.realstation.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import be.mikedhoore.realstation.Models.BoardItem;
import be.mikedhoore.realstation.Models.Station;
import be.mikedhoore.realstation.R;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {

    private ArrayList<BoardItem> boardItemArrayList;
    Context context;

    public BoardAdapter(ArrayList<BoardItem> boardItems, Context context1) {
        boardItemArrayList = boardItems;
        context = context1;
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.liveboarditem, parent, false);
        BoardViewHolder boardViewHolder = new BoardViewHolder(v);
        return boardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        BoardItem current = boardItemArrayList.get(position);
        holder.spoorDyna.setText(current.getTrack());
        holder.eindStationNaam.setText(current.getEndStation());
        Date dateTime = new Date(Long.parseLong(current.getTime()) * 1000);
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        String depart = context.getString(R.string.depart);
        String delay = "";
        if (Integer.parseInt(current.getDelay()) != 0) {
            delay = " + " + current.getDelay() + " min";
            holder.vertrekTijd.setTextColor(Color.RED);
        }
        String placeholder = depart + " " + time.format(dateTime) + delay;
        holder.vertrekTijd.setText(placeholder);
    }

    @Override
    public int getItemCount() {
        return boardItemArrayList.size();
    }

    public static class BoardViewHolder extends RecyclerView.ViewHolder {

        public TextView spoorDyna;
        public TextView eindStationNaam;
        public TextView vertrekTijd;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
            spoorDyna = itemView.findViewById(R.id.spoorDyna);
            eindStationNaam = itemView.findViewById(R.id.eindStationNaam);
            vertrekTijd = itemView.findViewById(R.id.vertrekTijd);
        }
    }

}
