package com.example.michal.vycvik;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michal.vycvik.API.ApiUtils;
import com.example.michal.vycvik.API.Models.ModelUser;
import com.example.michal.vycvik.Fragments.Adapters.SignedEventsAdapter;
import com.example.michal.vycvik.Fragments.EventsListFragment;
import com.example.michal.vycvik.Fragments.PostPreviewFragment;
import com.example.michal.vycvik.Fragments.SignedEventsFragment;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final int type_NOT_LOGGED = 0;
    private final int type_REGULAR = 1;
    private final int type_TUTOR= 2;
    private Toolbar toolbar;
    Drawer drawer;
    Call<ModelUser> call;
    ModelUser signedUser;
    Fragment fragmentSigned;

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen())
            drawer.closeDrawer();
        else super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        toolbar = setupToolbar();
        int type = type_REGULAR;


        final Fragment fragmentPreview = new PostPreviewFragment();
        final Fragment fragmentEvents = new EventsListFragment() {
            @Override
            public void startPostActivity(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                Fragment fragment = new PostPreviewFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        };

        fragmentSigned = new SignedEventsFragment() {
            @Override
            public void startPostActivity(int id) {

            }
        };

        fragmentTransaction.add(R.id.frag, fragmentEvents).commit();


        // App drawer

        switch (type){
            case 2:{
                drawer = new DrawerBuilder()
                        .withToolbar(toolbar)
                        .withActivity(this)
                        .withHeader(R.layout.drawer_header)
                        .withSliderBackgroundColorRes(R.color.colorGrayLight)
                        .withCloseOnClick(true)
                        .withSelectedItem(2)
                        .addDrawerItems(
                                new PrimaryDrawerItem()
                                        .withName("Dodaj wydarzenie")
                                        .withIdentifier(1)
                                        .withTextColorRes(R.color.colorTextLight)
                                        .withSelectedColorRes(R.color.colorGraySolid)
                                        .withSelectedTextColorRes(R.color.primary),
                                new PrimaryDrawerItem()
                                        .withName("Aktualne wydarzenia")
                                        .withIdentifier(2)
                                        .withTextColorRes(R.color.colorTextLight)
                                        .withSelectedColorRes(R.color.colorGraySolid)
                                        .withSelectedTextColorRes(R.color.primary),
                                new PrimaryDrawerItem()
                                        .withName("Zarządzaj wydarzeniami")
                                        .withIdentifier(3)
                                        .withTextColorRes(R.color.colorTextLight)
                                        .withSelectedColorRes(R.color.colorGraySolid)
                                        .withSelectedTextColorRes(R.color.primary),
                                new PrimaryDrawerItem()
                                        .withName("Powiadomienia")
                                        .withIdentifier(4)
                                        .withTextColorRes(R.color.colorTextLight)
                                        .withSelectedColorRes(R.color.colorGraySolid)
                                        .withSelectedTextColorRes(R.color.primary),
                                new PrimaryDrawerItem()
                                        .withName("Statusy płatności")
                                        .withIdentifier(5)
                                        .withTextColorRes(R.color.colorTextLight)
                                        .withSelectedColorRes(R.color.colorGraySolid)
                                        .withSelectedTextColorRes(R.color.primary)

                        )
                        .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                if(drawerItem != null){
                                    Fragment fragment = null;
                                     if(drawerItem.getIdentifier() == 1){
                                         fragment = fragmentPreview;
                                         toolbar.setTitle("Dodaj wydarzenie");
                                     }else if(drawerItem.getIdentifier() == 2){
                                         fragment = fragmentEvents;
                                         toolbar.setTitle("Wydarzenia");
                                     }else if(drawerItem.getIdentifier() == 3){

                                     }else if(drawerItem.getIdentifier() == 4){

                                     }else if(drawerItem.getIdentifier() == 5) {

                                     }
                                     if(fragment != null){
                                         FragmentManager fragmentManager = getFragmentManager();
                                         FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                         fragmentTransaction.replace(R.id.frag, fragment);
                                         fragmentTransaction.addToBackStack(null);
                                         fragmentTransaction.commit();
                                     }

                                }
                                return false;
                            }
                        })
                        .build();
                break;
            }
            case 1:{
                drawer = new DrawerBuilder()
                        .withToolbar(toolbar)
                        .withActivity(this)
                        .withHeader(R.layout.drawer_header)
                        .withSliderBackgroundColorRes(R.color.colorGrayLight)
                        .addDrawerItems(
                                new PrimaryDrawerItem()
                                        .withName("Aktualne wydarzenia")
                                        .withIdentifier(1)
                                        .withTextColorRes(R.color.colorTextLight)
                                        .withSelectedColorRes(R.color.colorGraySolid)
                                        .withSelectedTextColorRes(R.color.primary),
                                new PrimaryDrawerItem()
                                        .withName("Twoje wydarzenia")
                                        .withIdentifier(2)
                                        .withTextColorRes(R.color.colorTextLight)
                                        .withSelectedColorRes(R.color.colorGraySolid)
                                        .withSelectedTextColorRes(R.color.primary),
                                new PrimaryDrawerItem()
                                        .withName("Ranking tutorów")
                                        .withIdentifier(3)
                                        .withTextColorRes(R.color.colorTextLight)
                                        .withSelectedColorRes(R.color.colorGraySolid)
                                        .withSelectedTextColorRes(R.color.primary)
                        ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                if(drawerItem != null){
                                    Fragment fragment = null;
                                    if(drawerItem.getIdentifier() == 1){
                                        fragment = fragmentEvents;
                                        toolbar.setTitle("Aktualne szkolenia");
                                    }else if(drawerItem.getIdentifier() == 2){
                                        fragment = fragmentSigned;
                                        toolbar.setTitle("Twoje wydarzenia");
                                    }else if(drawerItem.getIdentifier() == 3){

                                    }

                                    if(fragment != null){
                                        FragmentManager fragmentManager = getFragmentManager();
                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.replace(R.id.frag, fragment);
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.commit();
                                    }

                                }
                                return false;
                            }
                        }).build();
                break;

            }
            default:{
                drawer = new DrawerBuilder()
                        .withToolbar(toolbar)
                        .withActivity(this)
                        .withHeader(R.layout.drawer_header)
                        .withSliderBackgroundColorRes(R.color.colorGrayLight)
                        .withTranslucentStatusBar(false)
                        .addDrawerItems(
                                new PrimaryDrawerItem()
                                        .withName("Aktualne wydarzenia")
                                        .withIdentifier(1)
                                        .withTextColorRes(R.color.colorTextLight)
                                        .withSelectedColorRes(R.color.colorGraySolid)
                                        .withSelectedTextColorRes(R.color.primary),
                                new PrimaryDrawerItem()
                                        .withName("Kategorie")
                                        .withIdentifier(2)
                                        .withTextColorRes(R.color.colorTextLight)
                                        .withTextColorRes(R.color.colorTextLight)
                                        .withSelectedColorRes(R.color.colorGraySolid)
                                        .withSelectedTextColorRes(R.color.primary),
                                new PrimaryDrawerItem()
                                        .withName("Logowanie")
                                        .withIdentifier(3)
                                        .withTextColorRes(R.color.colorTextLight)
                                        .withTextColorRes(R.color.colorTextLight)
                                        .withSelectedColorRes(R.color.colorGraySolid)
                                        .withSelectedTextColorRes(R.color.primary)
                        ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                if(drawerItem != null){
                                    Fragment fragment = null;
                                    if(drawerItem.getIdentifier() == 1){

                                    }else if(drawerItem.getIdentifier() == 2){
                                        fragment = fragmentEvents;
                                    }else if(drawerItem.getIdentifier() == 3) {
                                        fragment = fragmentEvents;
                                    }

                                    if(fragment != null){
                                        FragmentManager fragmentManager = getFragmentManager();
                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.replace(R.id.frag, fragment);
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.commit();
                                    }

                                }
                                return false;
                            }
                        })
                        .build();
                break;

            }
        }

    }


    Toolbar setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Wydarzenia");
        call = ApiUtils.getApiService().getUser(2);

        call.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, final Response<ModelUser> response) {

                new Utils.DownloadImageFromInternet() {
                    @Override
                    public void onPostExecute(Bitmap result) {
                        setupHeader(drawer, response.body().getName() + " " + response.body().getLastName(), 1, 4, result);
                    }
                }.execute(response.body().getAvatarUrl());

                signedUser = response.body();
                Bundle bundle = new Bundle();
                bundle.putInt("id", signedUser.getId());
                fragmentSigned.setArguments(bundle);
            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT ).show();

            }
        });
        return toolbar;
    }

     void setupHeader(Drawer drawer, String name, int type, int rate, Bitmap avatar){
         TextView textName = (TextView) drawer.getHeader().findViewById(R.id.header_name);
         ImageView avatarImage = (ImageView) drawer.getHeader().findViewById(R.id.header_avatar);
         avatarImage.setImageBitmap(avatar);
         textName.setText(name);

         TextView textView = (TextView) drawer.getHeader().findViewById(R.id.header_type);
         switch(type){
             case 2: {
                 textView.setText("Organizator");
                 break;
             }
             case 1: {
                 textView.setText("Uczestnik");
                 break;
             }
             default:{
                 textName.setText("Vycvik");
                 textView.setText("Kolektor Ofert Szkoleniowych");
                 break;
             }
         }

         ImageView star1 = (ImageView) drawer.getHeader().findViewById(R.id.star1);
         ImageView star2 = (ImageView) drawer.getHeader().findViewById(R.id.star2);
         ImageView star3 = (ImageView) drawer.getHeader().findViewById(R.id.star3);
         ImageView star4 = (ImageView) drawer.getHeader().findViewById(R.id.star4);
         ImageView star5 = (ImageView) drawer.getHeader().findViewById(R.id.star5);

         switch (rate){
             case 1: {
                 star1.setImageResource(R.drawable.ic_star_active_24dp);
                 star2.setImageResource(R.drawable.ic_star_empty_24dp);
                 star3.setImageResource(R.drawable.ic_star_empty_24dp);
                 star4.setImageResource(R.drawable.ic_star_empty_24dp);
                 star5.setImageResource(R.drawable.ic_star_empty_24dp);
                 break;
             }
             case 2: {
                 star1.setImageResource(R.drawable.ic_star_active_24dp);
                 star2.setImageResource(R.drawable.ic_star_active_24dp);
                 star3.setImageResource(R.drawable.ic_star_empty_24dp);
                 star4.setImageResource(R.drawable.ic_star_empty_24dp);
                 star5.setImageResource(R.drawable.ic_star_empty_24dp);
                 break;
             }
             case 3: {
                 star1.setImageResource(R.drawable.ic_star_active_24dp);
                 star2.setImageResource(R.drawable.ic_star_active_24dp);
                 star3.setImageResource(R.drawable.ic_star_active_24dp);
                 star4.setImageResource(R.drawable.ic_star_empty_24dp);
                 star5.setImageResource(R.drawable.ic_star_empty_24dp);
                 break;
             }
             case 4: {
                 star1.setImageResource(R.drawable.ic_star_active_24dp);
                 star2.setImageResource(R.drawable.ic_star_active_24dp);
                 star3.setImageResource(R.drawable.ic_star_active_24dp);
                 star4.setImageResource(R.drawable.ic_star_active_24dp);
                 star5.setImageResource(R.drawable.ic_star_empty_24dp);
                 break;
             }
             case 5: {
                 star1.setImageResource(R.drawable.ic_star_active_24dp);
                 star2.setImageResource(R.drawable.ic_star_active_24dp);
                 star3.setImageResource(R.drawable.ic_star_active_24dp);
                 star4.setImageResource(R.drawable.ic_star_active_24dp);
                 star5.setImageResource(R.drawable.ic_star_active_24dp);
                 break;
             }
             case -1: {
                 star1.setVisibility(View.INVISIBLE);
                 star2.setVisibility(View.INVISIBLE);;
                 star3.setVisibility(View.INVISIBLE);;
                 star4.setVisibility(View.INVISIBLE);;
                 star5.setVisibility(View.INVISIBLE);;
                 break;
             }
             default: {
                 star1.setImageResource(R.drawable.ic_star_empty_24dp);
                 star2.setImageResource(R.drawable.ic_star_empty_24dp);
                 star3.setImageResource(R.drawable.ic_star_empty_24dp);
                 star4.setImageResource(R.drawable.ic_star_empty_24dp);
                 star5.setImageResource(R.drawable.ic_star_empty_24dp);
                 break;
             }
         }

     }



}


