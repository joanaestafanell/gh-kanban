package joana.test.gh_kanban.model.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Singleton class to persist data.
 *
 * Accessed only by ViewModels and enables {@link android.arch.lifecycle.LiveData} objects to propagate changes.
 */
@Database(entities = {Board.class, BoardIssue.class}, version = 1, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {

    private static volatile LocalDatabase INSTANCE;

    public static LocalDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocalDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LocalDatabase.class, "local_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    public abstract BoardDao boardDao();
    public abstract BoardIssueDao issueDao();

}
