package dev.trindadeaquiles.lib.github;

import android.os.Bundle;
import android.util.Log;
import android.content.Context; 

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import dev.trindadeaquiles.lib.github.adapters.ContributorsAdapter;
import dev.trindadeaquiles.lib.github.api.github.Contributor;
import dev.trindadeaquiles.lib.github.api.github.GitHubService;
import dev.trindadeaquiles.lib.github.api.github.User;
import dev.trindadeaquiles.lib.databinding.GithubContributorsBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api  {

    public String baseUrl = "https://api.github.com/";
    public String onwer = "onwer";
    public String repo = "repo";
    
    public Context ctx;
        
    private GithubContributorsBinding binding;
    private ArrayList<HashMap<String, Object>> contributorsList = new ArrayList<>();
    private GitHubService service;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GithubContributorsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }*/
    
    public void initialize (Context context) {
        ctx = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GitHubService.class);

        fetchContributors();
    }

    public void fetchContributors () {
        Call<List<Contributor>> call = service.getContributors(onwer, repo);

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

    public void fetchUserBio (String username, String avatarUrl) {
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

    public void addContributor (String username, String bio, String avatarUrl) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("login", username);
        map.put("bio", bio);
        map.put("avatar-url", avatarUrl);
        contributorsList.add(map);
        binding.listCon.setAdapter(new ContributorsAdapter(ctx, contributorsList, binding.listCon));
    }
}