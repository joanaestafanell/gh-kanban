package joana.test.gh_kanban.model;

import android.arch.persistence.room.RoomDatabase;

/**
 * Singleton class to persist data.
 *
 * Accessed only by ViewModels and enables {@link android.arch.lifecycle.LiveData} objects to propagate changes.
 */

public abstract class Database extends RoomDatabase {

}
