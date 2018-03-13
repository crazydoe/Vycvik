package com.example.michal.vycvik.Fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.example.michal.vycvik.API.ApiService;
import com.example.michal.vycvik.API.ApiUtils;
import com.example.michal.vycvik.API.Models.ModelEvent;
import com.example.michal.vycvik.Fragments.Adapters.EventsAdapter;
import com.example.michal.vycvik.HidingScrollListener;
import com.example.michal.vycvik.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class EventsListFragment extends Fragment {

    private View view = null;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    private OnFragmentInteractionListener mListener;
    private Call<List<ModelEvent>> call;
    private ApiService apiService;


    public EventsListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_events_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.events_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }

            @Override
            public void onShow() {
                showViews();
            }
        });

        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        call = ApiUtils.getApiService().getEvents();
        call.enqueue(new Callback<List<ModelEvent>>() {
            @Override
            public void onResponse(Call<List<ModelEvent>> call, Response<List<ModelEvent>> response) {
                mAdapter = new EventsAdapter(response.body(), new EventsAdapter.Callback() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onItemClick(String text, int position) {
                        startPostActivity(position);

                    }
                });

                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<ModelEvent>> call, Throwable t) {
                Toast.makeText(getContext(), "Błąd połączenia "+ t.getMessage(), Toast.LENGTH_SHORT ).show();
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

    public abstract void startPostActivity(int id);
}
