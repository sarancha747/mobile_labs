package cn.stu.lab4.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import cn.stu.lab4.App;
import cn.stu.lab4.R;
import cn.stu.lab4.details.DetailsActivity;

public class MainActivity extends AppCompatActivity {

    private Button searchButton;
    private RecyclerView repositoriesList;
    private EditText usernameEditText;
    private ProgressBar progress;
    private TextView emptyTextView;
    private TextView errorTextView;

    private MainViewModel viewModel;

    private RepositoriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //searchButton = findViewById(R.id.searchButton);
        repositoriesList = findViewById(R.id.repositoriesList);
        //usernameEditText = findViewById(R.id.usernameEditText);
        progress = findViewById(R.id.progress);
        emptyTextView = findViewById(R.id.emptyTextView);
        errorTextView = findViewById(R.id.errorTextView);

        App app = (App) getApplication();
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, app.getViewModelFactory());
        viewModel = viewModelProvider.get(MainViewModel.class);

        viewModel.getViewState().observe(this, state -> {
            //searchButton.setEnabled(state.isEnableSearchButton());
            repositoriesList.setVisibility(toVisibility(state.isShowList()));
            progress.setVisibility(toVisibility(state.isShowProgress()));
            emptyTextView.setVisibility(toVisibility(state.isShowEmptyHint()));
            errorTextView.setVisibility(toVisibility(state.isShowError()));

            adapter.setRepositories(state.getRepositories());
        });


        viewModel.getRepositories();


        initRepositoriesList();
    }

    private void initRepositoriesList() {
        LayoutManager layoutManager = new LinearLayoutManager(this);
        repositoriesList.setLayoutManager(layoutManager);

        adapter = new RepositoriesAdapter(repository -> {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(DetailsActivity.EXTRA_REPOSITORY_ID, repository.getDate());
            startActivity(intent);
        });
        repositoriesList.setAdapter(adapter);
    }

    static int toVisibility(boolean show) {
        return show ? View.VISIBLE : View.GONE;
    }

}
