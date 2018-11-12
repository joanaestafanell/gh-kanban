package joana.test.gh_kanban.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import joana.test.gh_kanban.model.Repo;
import joana.test.gh_kanban.util.Constants;
import joana.test.gh_kanban.util.GitHubApiEndpoints;
import joana.test.gh_kanban.util.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Loads Remote data using Retrofit lib and updates views with data binding.
 */

public class ExploreViewModel extends ViewModel {

    final static String TAG = "ExploreViewModel";

    final MutableLiveData<List<Repo>> remoteData = new MutableLiveData<>();

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

    public void createKanban(Repo repo){
        Log.d(TAG, "card clicked! " + repo.getName());

        //Insert repo to db

    }

}
