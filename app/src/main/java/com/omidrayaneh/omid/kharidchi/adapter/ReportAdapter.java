package com.omidrayaneh.omid.kharidchi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omidrayaneh.omid.kharidchi.R;
import com.omidrayaneh.omid.kharidchi.activity.MainActivity;
import com.omidrayaneh.omid.kharidchi.helper.CalendarTool;
import com.omidrayaneh.omid.kharidchi.model.InvoiceModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {
    List<InvoiceModel> invoiceModels;
    Context mContext;
    Date date;

    public ReportAdapter(List<InvoiceModel> invoiceModels, Context mContext) {
        this.invoiceModels = invoiceModels;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_report, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {

        holder.txtId.setText(invoiceModels.get(position).getInvoiceNo() + "");
        if (invoiceModels.get(position).getStatuse() == 0) {
            holder.txtStatus.setText(R.string.suspend);
        } else if (invoiceModels.get(position).getStatuse() == 1) {
            holder.txtStatus.setText(R.string.sent);
        } else if (invoiceModels.get(position).getStatuse() == 2) {
            holder.txtStatus.setText(R.string.delivery);
        } else if (invoiceModels.get(position).getStatuse() == 3) {
            holder.txtStatus.setText(R.string.nonDelivery);
        }
        else if (invoiceModels.get(position).getStatuse() == 4)
        {
            holder.txtStatus.setText(R.string.cancelled);
        }
        int min_price = Integer.parseInt(MainActivity.min_cost.replace(".0", ""));
        int cost = Integer.parseInt(MainActivity.cost.replace(".0", ""));
        int total = Integer.parseInt(invoiceModels.get(position).getTotal().replace(".0", ""));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            date = dateFormat.parse(invoiceModels.get(position).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int year = (date.getYear() + 1900);
        int month = date.getMonth() + 1;
        int day = date.getDate();
        int houre = date.getHours();
        int minute = date.getMinutes();
        CalendarTool calendarTool=new CalendarTool();
        calendarTool.setGregorianDate(year,month,day);
       String date1= calendarTool.getIranianDate();



        holder.txtDate.setText( date1 );
        holder.txtTime.setText(houre+":"+minute);
        if (min_price < total) {
            holder.txtTotal.setText(String.valueOf(total) + " " + mContext.getString(R.string.toman));
        } else {
            holder.txtTotal.setText(String.valueOf(total + cost) + " " + mContext.getString(R.string.toman));

        }

    }

    @Override
    public int getItemCount() {
        return invoiceModels.size();
    }

   public class ReportViewHolder extends RecyclerView.ViewHolder {
        TextView txtId, txtDate,txtTime, txtTotal, txtStatus;

        ReportViewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.report_invoiceId);
            txtDate = itemView.findViewById(R.id.report_Date);
            txtTotal = itemView.findViewById(R.id.report_Price);
            txtStatus = itemView.findViewById(R.id.report_Status);
            txtTime = itemView.findViewById(R.id.txtTime);

        }
    }

}
