package com.example.inductionapppdfviewerv3;

//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;



public class MainActivity extends AppCompatActivity {
    private final String PDF_LINK = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/";
    private final String MY_PDF = "dummy.pdf";
    private AppCompatSeekBar seekBar;
    private PDFView pdfView;
    private TextView txtView;

//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pdfView = findViewById(R.id.pdfView);
        txtView = findViewById(R.id.txtView);

        initSeekBar();
        downloadPdf(MY_PDF);
    }

    private void initSeekBar() {
        seekBar = findViewById(R.id.seekBar);
        seekBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        seekBar.getThumb().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress, boolean fromUser) {
                int val = (progress * (seekBar.getWidth() - 3
                        * seekBar.getThumbOffset())) / seekBar.getMax();
                txtView.setText("" + progress);
                txtView.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


    }

    private void downloadPdf(final String fileName) {
        new AsyncTask<Void, Integer, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                return downloadPdf();
            }

            @Nullable
            private Boolean downloadPdf() {
                try {
                    File file = getFileStreamPath(fileName);
                    if (file.exists())
                        return true;

                    try {
                        FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                        URL u = new URL(PDF_LINK);
                        URLConnection conn = u.openConnection();
                        int contentLength = conn.getContentLength();
                        InputStream input = new BufferedInputStream(u.openStream());
                        byte data[] = new byte[contentLength];
                        long total = 0;
                        int count;
                        while ((count = input.read(data)) != -1) {
                            total += count;
                            publishProgress((int) ((total * 100) / contentLength));
                            fileOutputStream.write(data, 0, count);
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        input.close();
                        return true;
                    } catch (final Exception e) {
                        e.printStackTrace();
                        return false; // swallow a 404
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                seekBar.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean) {
                    openPdf(fileName);
                } else {
                    Toast.makeText(MainActivity.this, "Unable to download this Pdf",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    private void openPdf(String fileName) {
        try {
            File file = getFileStreamPath(fileName);
            Log.e("file", "file: " + file.getAbsolutePath());
            seekBar.setVisibility(View.GONE);
            pdfView.setVisibility(View.VISIBLE);
            pdfView.fromFile(file)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .onError((t) -> {
                            Log.e("file: ", "file: " + t.toString());
                    })
                    .enableAntialiasing(true)
                    .spacing(0)
                    .pageFitPolicy(FitPolicy.WIDTH)
                    .load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




