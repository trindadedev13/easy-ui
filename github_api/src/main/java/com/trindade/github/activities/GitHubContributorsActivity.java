package com.trindade.github.activites;

/*import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import com.trindade.github.adapters.ContributorsAdapter;
import com.trindade.github.api.github.Contributor;
import com.trindade.github.api.github.GitHubService;
import com.trindade.github.api.github.User;
import com.trindade.databinding.GithubContributorsBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;*/

public class GitHubContributorsActivity  {

    /*private static final String BASE_URL = "https://api.github.com/";
    private GithubContributorsBinding binding;
    private ArrayList<HashMap<String, Object>> contributorsList = new ArrayList<>();
    private GitHubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GithubContributorsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GitHubService.class);

        fetchContributors();
    }

    private void fetchContributors() {
        Call<List<Contributor>> call = service.getContributors("aquilesTrindade", "StringsCreator");

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Contributor> contributors = response.body();
                    for (Contributor contributor : contributors) {
                        fetchUserBio(contributor.getLogin(), contributor.getAvatarUrl());
                    }
                } else {
                    Log.e("GitHub API", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                Log.e("GitHub API", "Error: " + t.getMessage());
            }
        });
    }

    private void fetchUserBio(String username, String avatarUrl) {
        Call<User> userCall = service.getUser(username);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    String bio = user.getBio();
                    addContributor(username, bio, avatarUrl);
                } else {
                    Log.e("GitHub API", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("GitHub API", "Error: " + t.getMessage());
            }
        });
    }

    private void addContributor(String username, String bio, String avatarUrl) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("login", username);
        map.put("bio", bio);
        map.put("avatar-url", avatarUrl);
        contributorsList.add(map);
        binding.listCon.setAdapter(new ContributorsAdapter(GitHubContributorsActivity.this, contributorsList, binding.listCon));
    }*/
}