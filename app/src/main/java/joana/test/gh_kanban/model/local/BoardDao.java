package joana.test.gh_kanban.model.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface BoardDao {

    @Insert(onConflict = REPLACE)
    void insert(Board board);

    @Query("SELECT * from board_table ORDER BY id ASC")
    LiveData<List<Board>> getAllBoards();

    @Query("DELETE FROM board_table")
    void deleteAll();

}
