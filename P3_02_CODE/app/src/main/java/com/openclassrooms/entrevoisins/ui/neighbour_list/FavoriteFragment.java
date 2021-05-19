package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteFragment extends Fragment {

        @BindView(R.id.favorite_recycler_list)
        RecyclerView mFavoritesRecyclerView;

        private NeighbourApiService mApiService;
        private List<Neighbour> mNeighbours;

    public FavoriteFragment() { }

        public static FavoriteFragment newInstance() {
            FavoriteFragment mFavoritesFragment = new FavoriteFragment();
            return mFavoritesFragment;
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mApiService = DI.getNeighbourApiService();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);
            ButterKnife.bind(this, view);
            configureRecyclerView();
            Context context = view.getContext();
            mFavoritesRecyclerView = (RecyclerView) view;
            mFavoritesRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            mFavoritesRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            mFavoritesRecyclerView.setAdapter(new MyFavoritesRecyclerViewAdapter(mNeighbours , getContext()));
            ;
            return view;
        }

        private void configureRecyclerView(){
            this.mNeighbours = new ArrayList<>();
            this.mFavoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        /**
         * Init the List of neighbours
         */
        private void initList() {
            mNeighbours = mApiService.getNeighboursFavorites();
            mFavoritesRecyclerView.setAdapter(new MyFavoritesRecyclerViewAdapter(mNeighbours, getContext()));
        }

        @Override
        public void onResume() {
            super.onResume();
            initList();
        }

        @Override
        public void onStart() {
            super.onStart();
            EventBus.getDefault().register(this);
        }

        @Override
        public void onStop() {
            super.onStop();
            EventBus.getDefault().unregister(this);
        }

        /**
         * Fired if the user clicks on a delete button
         * @param event
         */
        @Subscribe
        public void onDeleteNeighbour(DeleteNeighbourEvent event) {
            if (event.fragment == 1) {
                mApiService.deleteNeighbourFavorites(event.neighbour);
                initList();
            }
        }
    }


