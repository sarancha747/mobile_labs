package cn.stu.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SetActivity extends AppCompatActivity {
    public static final String FIRST_MESSAGE = "FIRST_MESSAGE";
    public static final String SECOND_MESSAGE = "SECOND_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        Button buttonReset = findViewById(R.id.button_send);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v);
            }
        });
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        EditText editTextFirstName = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText editTextSecondName = (EditText) findViewById(R.id.editTextTextPersonName2);
        String messageTextFirstName = editTextFirstName.getText().toString();
        String messageTextSecondName = editTextSecondName.getText().toString();
        intent.putExtra(FIRST_MESSAGE, messageTextFirstName);
        intent.putExtra(SECOND_MESSAGE, messageTextSecondName);
        startActivity(intent);
        finish();
    }
}