package lyjak.anna.recogenre.model.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import lyjak.anna.recogenre.model.room.DAO.SongDAO;
import lyjak.anna.recogenre.model.room.entities.Song;

@Database(entities = {Song.class}, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {

    public SongDAO songDAO;

    private static volatile AppRoomDatabase INSTANCE;

    static AppRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
