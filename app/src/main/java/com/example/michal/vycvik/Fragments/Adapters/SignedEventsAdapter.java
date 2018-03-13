package com.example.michal.vycvik.Fragments.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michal.vycvik.API.ApiService;
import com.example.michal.vycvik.API.ApiUtils;
import com.example.michal.vycvik.API.Models.ModelEvent;
import com.example.michal.vycvik.R;
import java.util.List;

/**
 * Created by michal on 13.06.2017.
 */

public class SignedEventsAdapter extends RecyclerView.Adapter<SignedEventsAdapter.ViewHolder>{

    private List<ModelEvent> userEvents = null;
    private Callback callback;

    public SignedEventsAdapter(List<ModelEvent> body, Callback callback) {
        this.userEvents = body;
        this.callback = callback;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView titleText;
        private Button dateText;
        private Button locationText;
        private Button priceText;
        private Button previewButton;
        private Button signOutButton;



        public ViewHolder(View v) {
            super(v);

            titleText = (TextView) v.findViewById(R.id.signed_event_title);
            dateText = (Button) v.findViewById(R.id.signed_date);
            locationText = (Button) v.findViewById(R.id.signed_location);
            priceText = (Button) v.findViewById(R.id.signed_price);
            signOutButton = (Button) v.findViewById(R.id.signed_singout);

        }

        @Override
        public void onClick(View v) {


        }
    }


    public interface Callback {
        void onItemClick(String text, int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_signed_events_card, parent, false);
        return new SignedEventsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelEvent data = userEvents.get(position);
        holder.dateText.setText(data.getEventDate());
        holder.titleText.setText(data.getEventName());
        holder.locationText.setText(data.getLocalization());
        holder.priceText.setText(data.getCost() + " zł");
        holder.signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiUtils.getApiService().signOutFromTheLecture(1, data.getId());
                Toast.makeText(v.getContext(), "Opuściłeś wykład", Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(userEvents != null)
            return userEvents.size();
        else return 0;
    }
}
