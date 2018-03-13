package com.example.michal.vycvik.Fragments.Adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michal.vycvik.API.ApiUtils;
import com.example.michal.vycvik.API.Models.ModelEvent;
import com.example.michal.vycvik.API.Models.ModelUser;
import com.example.michal.vycvik.R;
import com.example.michal.vycvik.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by michal on 04.05.2017.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private List<ModelEvent> mEvents = null;
    public Callback callback;
    private Call<ModelUser> userCall;



    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        private TextView titleText;
        private TextView organizatorText;
        private TextView descText;
        private Button dateText;
        private Button locationText;
        private Button priceText;
        private ImageView instructorAvatar;



        public ViewHolder(View v) {
            super(v);
            titleText = (TextView) v.findViewById(R.id.list_post_title);
            organizatorText = (TextView) v.findViewById(R.id.list_post_organizator);
            descText = (TextView) v.findViewById(R.id.list_post_desc);
            dateText = (Button) v.findViewById(R.id.list_post_date);
            locationText = (Button) v.findViewById(R.id.list_post_location);
            priceText = (Button) v.findViewById(R.id.list_post_price);
            instructorAvatar = (ImageView) v.findViewById(R.id.list_post_avatar);
        }

        @Override
        public void onClick(View v) {


        }
    }

    public EventsAdapter(List<ModelEvent> events, Callback callback){
        mEvents = events;
        this.callback = callback;
    }

    public interface Callback {
        void onItemClick(String text, int position);
    }

    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_card, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ModelEvent data = mEvents.get(position);
        userCall = ApiUtils.getApiService().getUser(data.getInstructorId());
        userCall.enqueue(new retrofit2.Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                holder.organizatorText.setText(response.body().getName() + " "
                        + response.body().getLastName());
                new Utils.DownloadImageFromInternet() {
                    @Override
                    public void onPostExecute(Bitmap result) {
                        holder.instructorAvatar.setImageBitmap(result);
                    }
                }.execute(response.body().getAvatarUrl());
            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {

            }
        });

        holder.titleText.setText(data.getEventName());
        holder.locationText.setText(data.getLocalization());
        holder.priceText.setText(data.getCost() + " zł");
        holder.descText.setText(data.getDescription());
        holder.dateText.setText(data.getEventDate());




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onItemClick(String.valueOf(data.getId()), data.getId());
            }
        });



    }

    @Override
    public int getItemCount() {
        if(mEvents != null)
            return mEvents.size();
        else return 0;
    }
}
