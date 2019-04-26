package co.nanoconnect.studyserviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.util.Random;

public class OtherAidlJankenService extends Service {
    // じゃんけんの手の配列
    private final String[] HAND = { "グー", "チョキ", "パー" };
    // じゃんけんの結果の配列
    private final String[] RESULT = { "あいこ！", "あなたの負け！", "あなたの勝ち！" };

    private final String LOG_TAG = "Other Aidl:";

    /**
     * サービスがバインドされた時にコールバックされる
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "バインドされた！");
        showToast("OtherService:バインドされた");
            return mStub;
}
    // IAIDLSampleServiceの実装
    IOtherAIDLJankenService.Stub mStub = new IOtherAIDLJankenService.Stub() {
        // プレイヤの手とコンピュータの手を持っておく
        int inMyHand, inEnmHand;
        /**
         * 結果を文字列で返す
         */
        @Override
        public String getStrResult() throws RemoteException {
            Log.d(LOG_TAG, "結果を返すよ！");
            showToast("OtherService:結果：" + RESULT[(inMyHand - inEnmHand + 3) % 3]);
            return RESULT[(inMyHand - inEnmHand + 3) % 3];
        }

        /**
         * プレイヤの手の文字列を返す
         */
        @Override
        public String getStrMyHand(int inMyHand) throws RemoteException {
            Log.d(LOG_TAG, "プレイヤの手を返すよ！");
            this.inMyHand = inMyHand;
            showToast("OtherService:プレイヤの手：" + HAND[inMyHand]);
            return HAND[inMyHand];
        }

        /**
         * コンピュータの手を生成し、文字列で返す
         */
        @Override
        public String getStrEnmHand() throws RemoteException {
            Log.d(LOG_TAG, "コンピュータの手を返すよ！");
            inEnmHand = new Random().nextInt(2);
            showToast("OtherService:コンピュータの手：" + HAND[inEnmHand]);
            return HAND[inEnmHand];
        }
    };

    private void showToast(final String text){
        //Log.i(LOG_TAG, text);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast ts = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                ts.setGravity(Gravity.BOTTOM , 0, 100);
                ts.show();
            }
        });
    }
}
