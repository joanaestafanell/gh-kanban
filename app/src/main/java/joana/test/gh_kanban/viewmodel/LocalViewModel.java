package joana.test.gh_kanban.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import java.util.List;

import joana.test.gh_kanban.model.local.Board;
import joana.test.gh_kanban.util.LocalRepository;

/**
 * Loads local data using Room and updates views with data binding.
 */

public class LocalViewModel extends AndroidViewModel {

    private LocalRepository mLocalRepository;

    final LiveData<List<Board>> boards;
    final MutableLiveData<Board> mSelectedBoard = new MutableLiveData<>();

    public LocalViewModel(@NonNull Application application) {
        super(application);

        mLocalRepository = new LocalRepository(application);
        boards = mLocalRepository.getAllBoards();
    }

    public LiveData<List<Board>> getBoards() {
        return boards;
    }

    public void onClickCard(Board board){
        mSelectedBoard.setValue(board);
    }

    public MutableLiveData<Board> getSelectedBoard(){
        return mSelectedBoard;
    }
}
