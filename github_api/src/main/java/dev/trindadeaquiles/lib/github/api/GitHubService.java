package dev.trindadeaquiles.lib.github.api.github;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> getContributors(
        @Path("owner") String owner,
        @Path("repo") String repo
    );

    @GET("users/{username}")
    Call<User> getUser(
        @Path("username") String username
    );
}