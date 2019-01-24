package lyjak.anna.recogenre.model.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import lyjak.anna.recogenre.model.room.DAO.SongDAO;
import lyjak.anna.recogenre.model.room.entities.Song;

public class SongRepository {

    private SongDAO mSongDao;
    private LiveData<List<Song>> mAllSongs;

    public SongRepository(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        mSongDao = db.songDAO;
        mAllSongs = mSongDao.getAllSongs();
    }

    public LiveData<List<Song>> getAllSongs() {
        return mAllSongs;
    }

    public void insert(Song song) {
        new insertAsyncTask(mSongDao).execute(song);
    }


    private static class insertAsyncTask extends AsyncTask<Song, Void, Void> {

        private SongDAO mAsyncTaskDao;

        insertAsyncTask(SongDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Song... params) {
            mAsyncTaskDao.addSong(params[0]);
            return null;
        }
    }

}
