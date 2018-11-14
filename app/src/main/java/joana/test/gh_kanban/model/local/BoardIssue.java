package joana.test.gh_kanban.model.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "issues_table",
        indices = {@Index("board_id")},
        foreignKeys = @ForeignKey(entity = Board.class,
                parentColumns = "id",
                childColumns = "board_id",
                onDelete = CASCADE))
public class BoardIssue {
    @PrimaryKey
    @NonNull
    private Integer id;

    @ColumnInfo(name = "board_id")
    private Integer boardId;

    private String title;

    @ColumnInfo(name = "created_at")
    private String createdAt;

    private int comments;

    private String state;

    @ColumnInfo(name = "kanban_state")
    private String kanbanState;

    public BoardIssue(@NonNull Integer id, Integer boardId, String title, String createdAt, int comments, String state, String kanbanState) {
        this.id = id;
        this.boardId = boardId;
        this.title = title;
        this.createdAt = createdAt;
        this.comments = comments;
        this.state = state;
        this.kanbanState = kanbanState;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getComments() {
        return comments;
    }

    public String getState() {
        return state;
    }

    public String getKanbanState() {
        return kanbanState;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setKanbanState(String kanbanState) {
        this.kanbanState = kanbanState;
    }
}
