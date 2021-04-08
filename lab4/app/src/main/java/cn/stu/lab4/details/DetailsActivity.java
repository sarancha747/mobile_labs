package cn.stu.lab4.details;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import cn.stu.lab4.App;
import cn.stu.lab4.R;
import cn.stu.lab4.model.Holiday;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_REPOSITORY_ID = "REPOSITORY_ID";

    private TextView nameTextView;
    private TextView dateTextView;
    private TextView localNameTextView;
    private TextView fixedTextView;
    private TextView globalTextView;
    private TextView launchYearTextView;
    private ProgressBar progressBar;

    private DetailsViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nameTextView = findViewById(R.id.nameTextView);
        dateTextView = findViewById(R.id.date);
        localNameTextView = findViewById(R.id.localName);
        fixedTextView = findViewById(R.id.fixed);
        globalTextView = findViewById(R.id.global);
        launchYearTextView = findViewById(R.id.launchYear);
        progressBar = findViewById(R.id.progress);

        String repositoryId = getIntent().getStringExtra(EXTRA_REPOSITORY_ID);
        if (repositoryId == null) {
            throw new RuntimeException("There is no repository ID");
        }

        App app = (App) getApplication();
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, app.getViewModelFactory());
        viewModel = viewModelProvider.get(DetailsViewModel.class);

        viewModel.loadRepositoryById(repositoryId);

        viewModel.getResults().observe(this, result -> {
            switch (result.getStatus()) {
                case SUCCESS:
                    Holiday holiday = result.getData();
                    nameTextView.setText(holiday.getName());
                    dateTextView.setText(holiday.getDate());
                    localNameTextView.setText(holiday.getLocalName());
                    fixedTextView.setText(String.valueOf(holiday.isFixed()));
                    globalTextView.setText(String.valueOf(holiday.isGlobal()));
                    launchYearTextView.setText(String.valueOf(holiday.getLaunchYear()));
                    progressBar.setVisibility(View.GONE);
                    break;
                case EMPTY:
                case LOADING:
                    nameTextView.setText("");
                    dateTextView.setText("");
                    localNameTextView.setText("");
                    fixedTextView.setText("");
                    globalTextView.setText("");
                    launchYearTextView.setText("");
                    progressBar.setVisibility(View.GONE);
                    break;
            }
        });
    }

}
