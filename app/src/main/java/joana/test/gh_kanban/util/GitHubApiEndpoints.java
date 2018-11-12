package joana.test.gh_kanban.util;

import java.util.List;

import joana.test.gh_kanban.model.Issue;
import joana.test.gh_kanban.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *  Retrofit interface class with interested GitHub API calls.
 */
public interface GitHubApiEndpoints {

    //https://api.github.com/users/googlesamples/repos
    @GET("users/{user}/repos")
    Call<List<Repo>> getReposByUser(@Path("user") String user);

    //https://api.github.com/repos/googlesamples/android-AccelerometerPlay/issues
    //Default returns state=open
    @GET("repos/{owner}/{repo}/issues")
    Call<List<Issue>> getRepoIssues(@Path("owner") String owner, @Path("repo") String repo);
}
