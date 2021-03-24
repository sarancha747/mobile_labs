package cn.stu.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    public AppService service = new AppService();
    public MainActivity(){
        super(R.layout.activity_main);
    }
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            service = ((AppService.AppBinder) binder).getService();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
    @Override
    protected void onStart(){
        super.onStart();
        Intent intent = new Intent(this, AppService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }
    @Override
    protected void onStop(){
        super.onStop();
        unbindService(connection);
    }
    public boolean winnerCheck(Button[][] buttons){
        return service.checkForWin(buttons);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        if (savedInstanceState == null){
            SetFragment setFragment = new SetFragment();
            //MainFragment mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainer, setFragment,null)
                    .commit();
        }
    }
}
