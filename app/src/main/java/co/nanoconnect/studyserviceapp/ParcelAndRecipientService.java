package co.nanoconnect.studyserviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import nanoconnect.co.jp.PersonURL;


public class ParcelAndRecipientService extends Service {

    private final String LOG_TAG = "Parcel_Recipient:";

    /**
     * サービスがバインドされた時にコールバックされる
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "バインドされた！");
        showToast("Parce_Service:バインドされた");
        return mStub;
    }
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(LOG_TAG, "バインドが解除された！");
        showToast("Parce_Service:バインドが解除された");
        return false;
    }

    // IAIDLSampleServiceの実装
    ParcelableRecipientTestService.Stub mStub = new ParcelableRecipientTestService.Stub() {
        /**
         * 結果を文字列で返す
         */
        @Override
        public String getParceUser(String name) throws RemoteException {
            Log.d(LOG_TAG, "getParceUser");
            showToast(LOG_TAG);
            return name; //RESULT[(inMyHand - inEnmHand + 3) % 3];
        }

        /**
         * プレイヤの手の文字列を返す
         */
        @Override
        public String getParceMail(String mail) throws RemoteException {
            Log.d(LOG_TAG, "getParceMail");
            showToast(LOG_TAG);
            return mail; //HAND[inMyHand];
        }

        /**
         * コンピュータの手を生成し、文字列で返す
         */
        @Override
        public String getParceURL(String URL) throws RemoteException {
            Log.d(LOG_TAG, "getParceURL");
            showToast(LOG_TAG);
            return URL; //HAND[inEnmHand];
        }

        /**
         * コンピュータの手を生成し、文字列で返す
         */
        @Override
        public String getParceUrlContent(String URL) throws RemoteException {
            Log.d(LOG_TAG, "getParceUrlContent");
            showToast(LOG_TAG);
            return URL; //HAND[inEnmHand];
        }

        /**
         * コンピュータの手を生成し、文字列で返す
         */
        /*@Override
        PersonURL getAll(PersonURL person) throws RemoteException {
            Log.d(LOG_TAG, "getParceUrlContent");
            showToast(LOG_TAG);
            return person; //HAND[inEnmHand];
        }*/

    };

    private void showToast(final String text) {
        //Log.i(LOG_TAG, text);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast ts = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                ts.setGravity(Gravity.BOTTOM, 0, 100);
                ts.show();
            }
        });
    }
}
