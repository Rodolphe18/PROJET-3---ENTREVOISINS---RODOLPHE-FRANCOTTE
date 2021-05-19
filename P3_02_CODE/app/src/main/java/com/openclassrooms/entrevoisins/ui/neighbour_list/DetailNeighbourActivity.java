package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.detail_neighbour_image)
    ImageView mDetailNeighbourImage;
    @BindView(R.id.neighbour_name_image)
    TextView mNeighnourNameImage;
    @BindView(R.id.arrow_back)
    ImageView mArrowBack;

    @BindView(R.id.neighbour_name_cardview)
    TextView mNeighnourNameCardview;
    @BindView(R.id.location)
    ImageView mLocation;
    @BindView(R.id.location_text)
    TextView mLocationText;
    @BindView(R.id.phone_number)
    ImageView mPhoneNumber;
    @BindView(R.id.phone_number_text)
    TextView mPhoneNumberText;
    @BindView(R.id.adress)
    ImageView mAdress;
    @BindView(R.id.adress_text)
    TextView mAdressText;

    @BindView(R.id.about_me)
    TextView mAboutMe;
    @BindView(R.id.neighbour_description)
    TextView mNeighbourDescription;

    @BindView(R.id.fab_button)
    ImageView mFabButton;

    private Neighbour mNeighbour;
    private NeighbourApiService mApiService;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        ButterKnife.bind(this);
        int position = getIntent().getIntExtra("position", 0);
        mApiService = DI.getNeighbourApiService();
        mNeighbour = mApiService.getNeighbourByPosition(position);

        mNeighnourNameImage.setText(mNeighbour.getName());
        mNeighnourNameCardview.setText(mNeighbour.getName());;
        mLocationText.setText(mNeighbour.getAddress());;
        mAdressText.setText(mNeighbour.getAddress());;
        mPhoneNumberText.setText(mNeighbour.getPhoneNumber());;
        mNeighbourDescription.setText(mNeighbour.getAboutMe());;

        Glide.with(getApplicationContext())
                .load(mNeighbour.getAvatarUrl())
                .apply(RequestOptions.centerCropTransform())
                .into(mDetailNeighbourImage);

        mArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onBackPressed(); }

        });
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( ! mApiService.getNeighboursFavorites().contains(mNeighbour)) {
                    mApiService.addNeighbourFavorites(mNeighbour);
                }
                if ( mApiService.getNeighboursFavorites().contains(mNeighbour))
                {    mFabButton.setImageResource(R.drawable.ic_full_star);}
                else {
                    mFabButton.setImageResource(R.drawable.ic_empty_star);
                }
            }
        });

    }}






