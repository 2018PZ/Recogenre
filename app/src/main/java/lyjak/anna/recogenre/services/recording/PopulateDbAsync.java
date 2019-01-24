package lyjak.anna.recogenre.services.recording;

import android.os.AsyncTask;

import lyjak.anna.recogenre.model.room.AppRoomDatabase;
import lyjak.anna.recogenre.model.room.DAO.SongDAO;
import lyjak.anna.recogenre.model.room.entities.Song;

public class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final SongDAO mDao;

    public PopulateDbAsync(AppRoomDatabase db) {
        mDao = db.songDAO;
    }

    @Override
    protected Void doInBackground(final Void... params) {
        mDao.deleteAll();
        Song song = new Song();
        song.setFileName("name1");
        song.setPath("name1");
        mDao.addSong(song);
        return null;
    }

}
