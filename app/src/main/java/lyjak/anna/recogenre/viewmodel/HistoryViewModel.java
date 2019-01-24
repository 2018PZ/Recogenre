package lyjak.anna.recogenre.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import lyjak.anna.recogenre.model.room.SongRepository;
import lyjak.anna.recogenre.model.room.entities.Song;

public class HistoryViewModel extends AndroidViewModel {

    private SongRepository repo;

    private LiveData<List<Song>> allSongs;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        repo = new SongRepository(application);
//        allSongs = repo.getAllSongs();
    }

    public LiveData<List<Song>> getAllSongs() {
        return allSongs;
    }

    public void insert(Song song) {
        repo.insert(song);
    }
}
