package cn.stu.lab1;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private Button[][] buttons = new Button[3][3];
    private boolean winChecker;
    private boolean player1Turn = true;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private String firstPlayerName;
    private String secondPlayerName;
    private MainActivity mainActivity = new MainActivity();
    public static final String FIRST_MESSAGE = "FIRST_MESSAGE";
    public static final String SECOND_MESSAGE = "SECOND_MESSAGE";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewPlayer1 = view.findViewById(R.id.text_view_p1);
        textViewPlayer2 = view.findViewById(R.id.text_view_p2);
        firstPlayerName = getArguments().getString(FIRST_MESSAGE);
        secondPlayerName = getArguments().getString(SECOND_MESSAGE);
        textViewPlayer1.setText(firstPlayerName + ": " + "0");
        textViewPlayer2.setText(secondPlayerName + ": " + "0");
        //setContentView(R.layout.activity_home);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getActivity().getPackageName());
                buttons[i][j] = view.findViewById(resID);
                buttons[i][j].setOnClickListener(this);

            }
        }
        Button buttonReset = view.findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
        Button buttonExit = view.findViewById(R.id.button_exit);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitGame();
            }
        });
        }
    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }
        roundCount++;

        //THREAD
        MyWorkerThread mWorkerThread = new MyWorkerThread("myWorkerThread");
        Runnable task = new Runnable() {
            @Override
            public void run() {
                winChecker = checkForWin(buttons);
                mWorkerThread.quit();
                }
        };
        mWorkerThread.start();
        mWorkerThread.prepareHandler();
        mWorkerThread.postTask(task);
        try {
            mWorkerThread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //THREAD

        if (winChecker) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
        winChecker = false;
    }
    public boolean checkForWin(Button[][] buttons) {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }
    private void player1Wins() {
        player1Points++;
        //Toast.makeText(this, firstPlayerName + " wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void player2Wins() {
        player2Points++;
        //Toast.makeText(this, secondPlayerName + " wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void draw() {
        //Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void updatePointsText() {
        textViewPlayer1.setText(firstPlayerName +": " + player1Points);
        textViewPlayer2.setText(secondPlayerName +": " + player2Points);
    }
    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;
    }
    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }
    private void exitGame() {
        System.exit(0);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //super.onRestoreInstanceState(savedInstanceState);
        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}
