package cn.stu.lab1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

public class AppService  extends Service {

    private AppBinder binder = new AppBinder();

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("AppService", "onCreate");
        System.out.println("onCreateService");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("AppService", "onDestroy");
    }
    @Override
    public IBinder onBind(Intent intent){
        return binder;
    }

    public boolean checkForWin(Button[][] buttons) {
        System.out.println("biba");
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

    class AppBinder extends Binder {
        public AppService getService(){
            return AppService.this;
        }
    }
}
