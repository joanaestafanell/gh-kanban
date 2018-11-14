package joana.test.gh_kanban.model.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface BoardIssueDao {

    @Insert(onConflict = REPLACE)
    void insert(BoardIssue issue);

    @Insert(onConflict = REPLACE)
    void insertAll(List<BoardIssue> issues);

    @Query("SELECT * FROM issues_table")
    LiveData<List<BoardIssue>> getAllIssues();

    @Query("SELECT * FROM issues_table WHERE board_id = :boardId")
    LiveData<List<BoardIssue>> getAllIssuesByBoardId(Integer boardId);

    @Query("SELECT * FROM issues_table WHERE kanban_state LIKE :state")
    LiveData<List<BoardIssue>> getAllIssuesByState(String state);

    @Query("SELECT * FROM issues_table WHERE board_id = :boardId AND kanban_state = :state")
    LiveData<List<BoardIssue>> getFilteredIssuesByBoardId(Integer boardId, String state);

    @Query("UPDATE issues_table SET kanban_state = :kanban_state WHERE id = :id")
    void updateIssueKanbanStateById(Integer id, String kanban_state);


    @Query("DELETE FROM issues_table")
    void deleteAll();

}
