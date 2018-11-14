package joana.test.gh_kanban.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import joana.test.gh_kanban.model.local.BoardIssue;
import joana.test.gh_kanban.util.LocalRepository;
import joana.test.gh_kanban.util.Utils;

public class KanbanViewModel extends AndroidViewModel {

    private LocalRepository mLocalRepository;
    private LiveData<List<BoardIssue>> mIssueList;

    public KanbanViewModel(@NonNull Application application) {
        super(application);
        mLocalRepository = new LocalRepository(application);
    }

    public void initBoardIssues(int boardId, String kanbanState){
        mIssueList = mLocalRepository.getIssuesBy(new Integer(boardId), kanbanState);
    }

    public LiveData<List<BoardIssue>> getIssueList() {
        return mIssueList;
    }

    public void onClickMoveRight(BoardIssue issue){
        int state = Utils.getStatePosition(issue.getKanbanState());
        String nextState = Utils.getKanbanStateByIndex(state+1);
        // Move issue to next state
        mLocalRepository.updateIssueState(issue.getId(), nextState);
    }

    public void onClickMoveLeft(BoardIssue issue){
        int state = Utils.getStatePosition(issue.getKanbanState());
        String nextState = Utils.getKanbanStateByIndex(state-1);
        // Move issue to next state
        mLocalRepository.updateIssueState(issue.getId(), nextState);
    }
}
