package joana.test.gh_kanban.util;

import android.app.Application;
import android.arch.lifecycle.LifecycleService;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import joana.test.gh_kanban.model.local.Board;
import joana.test.gh_kanban.model.local.BoardDao;
import joana.test.gh_kanban.model.local.BoardIssue;
import joana.test.gh_kanban.model.local.BoardIssueDao;
import joana.test.gh_kanban.model.local.LocalDatabase;

public class LocalRepository {

    private BoardDao mBoardDao;
    private BoardIssueDao mIssueDao;
    private LiveData<List<Board>> mAllBoards;

    public LocalRepository(Application application) {

        LocalDatabase db = LocalDatabase.getDatabase(application);
        mBoardDao = db.boardDao();
        mIssueDao = db.issueDao();

        mAllBoards = mBoardDao.getAllBoards();
    }

    public LiveData<List<Board>> getAllBoards() {
        return mAllBoards;
    }

    public LiveData<List<BoardIssue>> getIssuesBy(Integer boardId, String kanbanState){
           return mIssueDao.getFilteredIssuesByBoardId(boardId, kanbanState);

    }

    public void addBoard(Board board){
        new addBoardAsyncTask(mBoardDao).execute(board);
    }

    public void addIssues(List<BoardIssue> issuesList){
        new addIssuesAsyncTask(mIssueDao).execute(issuesList);
    }

    public void updateIssueState(Integer issueId, String kanbanState){
        new updateIssueStateAsyncTask(mIssueDao, issueId, kanbanState).execute();
    }

    private static class addBoardAsyncTask extends AsyncTask<Board, Void, Void> {

        private BoardDao mBoardDao;

        public addBoardAsyncTask(BoardDao mBoardDao) {
            this.mBoardDao = mBoardDao;
        }

        @Override
        protected Void doInBackground(Board... boards) {
            mBoardDao.insert(boards[0]);
            return null;
        }
    }

    private static class updateIssueStateAsyncTask extends AsyncTask<Void, Void, Void> {

        private BoardIssueDao mIssueDao;
        private Integer mIssueId;
        private String mKanbanState;

        public updateIssueStateAsyncTask(BoardIssueDao issueDao, Integer issueId, String kanbanState) {
            this.mIssueDao = issueDao;
            this.mIssueId = issueId;
            this.mKanbanState = kanbanState;
        }

        @Override
        protected Void doInBackground(Void... params) {
            mIssueDao.updateIssueKanbanStateById(mIssueId, mKanbanState);
            return null;
        }
    }

    private static class addIssuesAsyncTask extends AsyncTask<List<BoardIssue>, Void, Void> {

        private BoardIssueDao mIssueDao;

        public addIssuesAsyncTask(BoardIssueDao mIssueDao) {
            this.mIssueDao = mIssueDao;
        }

        @Override
        protected Void doInBackground(List<BoardIssue>... lists) {
            mIssueDao.insertAll(lists[0]);
            return null;
        }
    }
}
