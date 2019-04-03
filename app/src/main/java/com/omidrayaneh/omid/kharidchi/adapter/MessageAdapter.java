package com.omidrayaneh.omid.kharidchi.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.helper.CalendarTool;
import com.omidrayaneh.omid.kharidchi.model.MessageModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MessageAdapter  extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    List<MessageModel> messageModels;
    Context mContext;
    Date date;

    public MessageAdapter(List<MessageModel> messageModels, Context mContext) {
        this.messageModels = messageModels;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageViewHolder holder, int position) {

        holder.txtTitle.setText(messageModels.get(position).getTitle());
        holder.txtContent.setText(messageModels.get(position).getContent());


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            date = dateFormat.parse(messageModels.get(position).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int year = (date.getYear() + 1900);
        int month = date.getMonth() + 1;
        int day = date.getDate();
        CalendarTool calendarTool=new CalendarTool();
        calendarTool.setGregorianDate(year,month,day);
        String date1= calendarTool.getIranianDate();


        holder.txtDate.setText(date1);
    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }
    public class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle,txtContent,txtDate;
        public MessageViewHolder(View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtContent=itemView.findViewById(R.id.txt_Content);
            txtDate=itemView.findViewById(R.id.date_message);
        }
    }
}

