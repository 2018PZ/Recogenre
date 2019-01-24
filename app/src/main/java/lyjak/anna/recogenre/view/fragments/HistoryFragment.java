package lyjak.anna.recogenre.view.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import lyjak.anna.recogenre.R;
import lyjak.anna.recogenre.model.room.entities.Song;
import lyjak.anna.recogenre.view.adapters.SongListAdapter;
import lyjak.anna.recogenre.viewmodel.HistoryViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private static final String TAG = HistoryFragment.class.getName();

    private HistoryViewModel historyViewModel;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.history_recycled_view);
        final SongListAdapter adapter = new SongListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);

        Log.i(TAG, "HistoryViewModel created");

//        historyViewModel.getAllSongs().observe(this, new Observer<List<Song>>() {
//            @Override
//            public void onChanged(@Nullable final List<Song> songs) {
//                // Update the cached copy of the words in the adapter.
//                adapter.setSongs(songs);
//            }
//        });

        return view;
    }

}
