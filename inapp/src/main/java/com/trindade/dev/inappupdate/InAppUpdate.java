package com.trindade.dev.inappupdate;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import androidx.core.content.FileProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import com.trindade.R;
import com.trindade.dev.util.*;

public class InAppUpdate implements RequestListener {

    private String updDevice;
    
    private String packageName;
    
    private int versionCode;
    private int dlProgress;
    private int nacho;
    
    private Context context;
        
    private InAppListener inAppListener;
    
    private RequestListener requestListener;
    
    public InAppUpdate(Context context, InAppListener inAppListener) {
        this.context = context;
        this.inAppListener = inAppListener;
        initializeParams();
        startRequest();
    }
    
    private void initializeParams() {
        updDevice = Build.ID;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
            packageName = packageInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void onResponse(String rTag, String rResponse, HashMap<String, Object> rResponseHeaders) {
          try {
              JSONObject _rResponse = new JSONObject(rResponse);
              inAppListener.onInitialize(_rResponse);
          } catch (JSONException e) {
              inAppListener.onError(e.toString());
          }
    }
    
    @Override
    public void onErrorResponse(String rTag, String rResponse) {
        inAppListener.onError(rResponse);
    }
  
    public void downloadUpdate(String downloadUrl, String downloadName) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            final String urlDownload = downloadUrl;
            final String fileName = downloadName;

            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(urlDownload));
            request.setDescription(string(R.string.downloading) + urlDownload);
            request.setTitle(fileName);
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

            final DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            final long downloadId = manager.enqueue(request);

            new Thread(() -> {
                boolean downloading = true;
                while (downloading) {
                    DownloadManager.Query q = new DownloadManager.Query();
                    q.setFilterById(downloadId);
                    
                    Cursor cursor = manager.query(q);
                    
                    if (cursor != null && cursor.moveToFirst()) {
                        int bytes_downloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                        int bytes_total = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                        int dlProgress = (int) ((bytes_downloaded * 100L) / bytes_total);
                        
                        if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                            downloading = false;
                        }
                        cursor.close();
                        ((Activity) context).runOnUiThread(() -> {
                            nacho = dlProgress;
                            if (nacho == 100) {
                                installUpdate(context, downloadName);
                            }
                        });
                    }
                }
            }).start();
        } else {
            showMessage(context, string(R.string.no_internet));
        }
    }

    public int downloadProgress() {
        return nacho;
    }

    public boolean isDownloadEnd() {
        return dlProgress == 100;
    }

    private void startRequest() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("packageName", packageName);
        map.put("versionCode", String.valueOf(versionCode));
        map.put("deviceId", updDevice);
        map.put("appId", "");
        map.put("deviceModel", Build.MODEL);
        map.put("deviceBrand", Build.BRAND);
        map.put("androidVersion", Build.VERSION.RELEASE);
        map.put("library", "4");
        map.put("store_active", "false");

        RequestNetwork requestNetwork = new RequestNetwork((Activity) context);
        requestNetwork.setParams(map, RequestNetworkController.REQUEST_BODY);
        requestNetwork.startRequestNetwork(RequestNetworkController.PUT, "https://mobsoft-services-update.up.railway.app/update/service/receive", "", this);
    }

    public void installUpdate(Context context, String fileName) {
        String PATH = FileUtil.getPublicDir(Environment.DIRECTORY_DOWNLOADS) + "/" + fileName;
        java.io.File file = new java.io.File(PATH);
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uriFromFile(context, new java.io.File(PATH)), "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                showMessage(context, string(R.string.error_on_open));
            }
        } else {
            Toast.makeText(context, string(R.string.installing), Toast.LENGTH_LONG).show();
        }
    }

    Uri uriFromFile(Context context, java.io.File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
        } else {
            return Uri.fromFile(file);
        }
    }

    private void showMessage(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }
    
    private String string(int resID) {
        return context.getResources().getString(resID);
    }
}