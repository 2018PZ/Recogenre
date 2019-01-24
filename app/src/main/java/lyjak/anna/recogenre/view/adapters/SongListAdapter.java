package lyjak.anna.recogenre.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lyjak.anna.recogenre.R;
import lyjak.anna.recogenre.model.room.entities.Song;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.SongViewHolder> {

    private final LayoutInflater mInflater;
    private List<Song> mSongs; // Cached copy of words

    public SongListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new SongViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        if (mSongs != null) {
            Song current = mSongs.get(position);
            holder.songItemView.setText(current.getFileName());
        } else {
            // Covers the case of data not being ready yet.
            holder.songItemView.setText("No Songs");
        }
    }

    public void setSongs(List<Song> songs){
        mSongs = songs;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mSongs != null)
            return mSongs.size();
        else return 0;
    }

    class SongViewHolder extends RecyclerView.ViewHolder {
        private final TextView songItemView;

        private SongViewHolder(View itemView) {
            super(itemView);
            songItemView = itemView.findViewById(R.id.textViewPath);
        }
    }
}
