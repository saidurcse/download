package cmed.com.download.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cmed.com.download.R;
import cmed.com.download.model.Download;
import cmed.com.download.service.DownloadService;
import cmed.com.download.util.PermissionUtils;


public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_PROGRESS = "message_progress";
    private static final int PERMISSION_REQUEST_CODE = 1;

    @BindView(R.id.progress)
    ProgressBar mProgressBar;
    @BindView(R.id.progress_text)
    TextView mProgressText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        registerReceiver();
    }

    @OnClick(R.id.btn_download)
    public void downloadFile(){

        PermissionUtils.SetAllPermission(this);
        startDownload();
    }

    private void startDownload(){

        Intent intent = new Intent(this,DownloadService.class);
        startService(intent);

    }

    private void registerReceiver(){

        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MESSAGE_PROGRESS);
        bManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals(MESSAGE_PROGRESS)){

                Download download = intent.getParcelableExtra("download");
                mProgressBar.setProgress(download.getProgress());
                if(download.getProgress() == 100){

                    mProgressText.setText("File Download Complete");

                } else {

                    mProgressText.setText(String.format("Downloaded (%d/%d) MB",download.getCurrentFileSize(),download.getTotalFileSize()));

                }
            }
        }
    };
}
