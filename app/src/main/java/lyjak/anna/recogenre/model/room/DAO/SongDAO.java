package lyjak.anna.recogenre.model.room.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import lyjak.anna.recogenre.model.room.entities.Song;

@Dao
public interface SongDAO {

    @Query("SELECT * FROM songs")
    public LiveData<List<Song>> getAllSongs();

    @Query("DELETE FROM songs")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addSong(Song song);

    @Delete
    public void deleteSong(Song product);

    @Update
    public void updateSong(Song product);

}
