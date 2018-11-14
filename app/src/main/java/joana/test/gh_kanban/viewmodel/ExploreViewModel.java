package joana.test.gh_kanban.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import joana.test.gh_kanban.model.local.Board;
import joana.test.gh_kanban.model.local.BoardIssue;
import joana.test.gh_kanban.model.remote.Issue;
import joana.test.gh_kanban.model.remote.Repo;
import joana.test.gh_kanban.util.Constants;
import joana.test.gh_kanban.util.GitHubApiEndpoints;
import joana.test.gh_kanban.util.LocalRepository;
import joana.test.gh_kanban.util.RetrofitClient;
import joana.test.gh_kanban.util.SingleLiveEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Loads Remote data using Retrofit lib and updates views with data binding.
 */

public class ExploreViewModel extends AndroidViewModel {

    final static String TAG = "ExploreViewModel";

    private LocalRepository mLocalRepository;

    final MutableLiveData<List<Repo>> remoteData = new MutableLiveData<>();
    final SingleLiveEvent<Void> mBoardAddedSingleEvent = new SingleLiveEvent<Void>();

    public ExploreViewModel(Application application) {
        super(application);
        mLocalRepository = new LocalRepository(application);
    }

    public void loadData() {
        //TODO Pagination and endless scroll in RecyclerView should be implemented. Out of scope to simplify.
        GitHubApiEndpoints apiService = RetrofitClient.getRetrofitInstance().create(GitHubApiEndpoints.class);
        Call<List<Repo>> call = apiService.getReposByUser(Constants.GH_REPO_USER);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if(response.isSuccessful())
                    remoteData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
               remoteData.setValue(null);
            }
        });

    }

    public MutableLiveData<List<Repo>> getRepos(){
        return remoteData;
    }

    public SingleLiveEvent<Void> onRepoClick(){
        return mBoardAddedSingleEvent;
    }

    public void createKanban(final Repo repo){
        //Init kanban board and issues
        String owner = repo.getOwner().getLogin();
        String name = repo.getName();
        final Board board = new Board(repo.getId(), name, owner);
        mLocalRepository.addBoard(board);

        //Load issues
        GitHubApiEndpoints apiService = RetrofitClient.getRetrofitInstance().create(GitHubApiEndpoints.class);
        Call<List<Issue>> call = apiService.getRepoIssues(owner, name);
        call.enqueue(new Callback<List<Issue>>() {
            @Override
            public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                if(response.isSuccessful()){
                    List<Issue> issues = response.body();
                    if(issues.size() > 0){
                        List<BoardIssue> issuesList = new ArrayList<>();
                        for (Issue issue: issues) {
                            BoardIssue boardIssue = new BoardIssue(issue.getId(),
                                    board.getId(),
                                    issue.getTitle(),
                                    issue.getCreatedAt(),
                                    issue.getComments().intValue(),
                                    issue.getState(),
                                    Constants.KanbanState.BACKLOG.toString());
                            issuesList.add(boardIssue);
                        }
                        mLocalRepository.addIssues(issuesList);
                    }
                    mBoardAddedSingleEvent.call();
                }
            }

            @Override
            public void onFailure(Call<List<Issue>> call, Throwable t) {
               //TODO Error control out of scope to simpify. Could be done with SingleLiveEvent
            }
        });


    }

}
