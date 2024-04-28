package com.example.testtingitemdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder>
{

    private Context context;
    private List<CardModel> cardList;
    private CardListener listener;

    public CardAdapter(Context context, List<CardModel> cardList, CardListener listener)
    {
        this.context = context;
        this.cardList = cardList;
        this.listener = listener;
    }
    public void filteredList(List<CardModel> cardList)
    {
        this.cardList = cardList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        int pos = position;
        holder.id.setText(String.valueOf(cardList.get(pos).getImgRID()));
        holder.imgRID.setImageResource(cardList.get(pos).getImgRID());
        holder.name.setText(cardList.get(pos).getName());
        holder.category.setText(cardList.get(pos).getCategory());

        holder.cv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onItemClicked(cardList.get(pos));
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return cardList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView id, name, category;
        private ImageView imgRID;
        private CardView cv;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            id = itemView.findViewById(R.id.tvID);
            name = itemView.findViewById(R.id.tvName);
            category = itemView.findViewById(R.id.tvCategory);
            imgRID = itemView.findViewById(R.id.ivIcon);
            cv = itemView.findViewById(R.id.cardView);
        }
    }
}
