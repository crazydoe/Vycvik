package com.example.michal.vycvik.Fragments;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michal.vycvik.API.ApiUtils;
import com.example.michal.vycvik.API.Models.ModelEvent;
import com.example.michal.vycvik.API.Models.ModelUser;
import com.example.michal.vycvik.R;
import com.example.michal.vycvik.Utils;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostPreviewFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ScrollView scrollView;
    int eventId;

    private TextView title;
    private TextView instructorName;
    private TextView description;
    private TextView detInstructorName;
    private TextView detInstructorDesc;
    private TextView price;
    private TextView location;
    private TextView avaible;
    private TextView date;
    private TextView time;
    private Button signUp;
    private Call<ModelEvent> call;
    private Call<ModelUser> iCall;
    private ImageView smallAvatar;
    private ImageView largeAvatar;


    public PostPreviewFragment() {
        // Required empty public constructor
    }


    public static PostPreviewFragment newInstance(String param1, String param2) {
        PostPreviewFragment fragment = new PostPreviewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_preview, container, false);
        eventId = getArguments().getInt("id");

        scrollView = (ScrollView) view.findViewById(R.id.post_scroll_view);
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY < oldScrollY)
                    hideViews();
                else showViews();
            }
        });


        title = (TextView) view.findViewById(R.id.post_title);
        instructorName = (TextView) view.findViewById(R.id.post_instructor_name);
        description = (TextView) view.findViewById(R.id.post_description);
        detInstructorDesc = (TextView) view.findViewById(R.id.post_det_instructor_desc);
        detInstructorName = (TextView) view.findViewById(R.id.post_det_instructor_name);
        price = (TextView) view.findViewById(R.id.post_price);
        location = (TextView) view.findViewById(R.id.post_location);
        avaible = (TextView) view.findViewById(R.id.avaible);
        date = (TextView) view.findViewById(R.id.post_date);
        time = (TextView) view.findViewById(R.id.post_time);
        smallAvatar = (ImageView) view.findViewById(R.id.post_instructor_img);
        largeAvatar = (ImageView) view.findViewById(R.id.post_det_instructor_img);
        signUp = (Button) view.findViewById(R.id.post_sign_up);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiUtils.getApiService().signUpToTheLecture(2, eventId);
                Toast.makeText(getContext(), "Zapisano na szkolenie", Toast.LENGTH_SHORT).show();
            }
        });


        call = ApiUtils.getApiService().getEvent(eventId);
        call.enqueue(new Callback<ModelEvent>() {
            @Override
            public void onResponse(Call<ModelEvent> call, Response<ModelEvent> response) {
                title.setText(response.body().getEventName());
                description.setText(response.body().getDescription());
//                price.setText(response.body().getCost());
                location.setText(response.body().getLocalization());
                avaible.setText(response.body().getMaxMembersCount() + " miejsc");
                date.setText(response.body().getEventDate());
                time.setText(response.body().getEventTime());
                eventId = response.body().getId();

                iCall = ApiUtils.getApiService().getUser( response.body().getInstructorId());
                iCall.enqueue(new Callback<ModelUser>() {
                    @Override
                    public void onResponse(Call<ModelUser> call, final Response<ModelUser> response) {
                        instructorName.setText(response.body().getName() + " " + response.body().getLastName());
                        detInstructorName.setText(response.body().getName() + " " + response.body().getLastName());
                        detInstructorDesc.setText(response.body().getDescription());

                        new Utils.DownloadImageFromInternet() {
                            @Override
                            public void onPostExecute(Bitmap result) {
                                smallAvatar.setImageBitmap(result);
                                largeAvatar.setImageBitmap(result);
                            }
                        } .execute(response.body().getAvatarUrl());

                    }
                    @Override
                    public void onFailure(Call<ModelUser> call, Throwable t) {

                    }
                });
            }
            @Override
            public void onFailure(Call<ModelEvent> call, Throwable t) {

            }
        });




        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void hideViews() {
        Toolbar mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
    }

    private void showViews() {
        Toolbar mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }


}
