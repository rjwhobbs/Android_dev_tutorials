package com.example.inductionapppdfviewerv2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_PDF_CODE = 1000;
    Button btn_open_agenda, btn_open_storage, btn_open_assets_one,
            btn_open_assets_two, btn_open_assets_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new BaseMultiplePermissionsListener(){
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        super.onPermissionsChecked(report);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        super.onPermissionRationaleShouldBeShown(permissions, token);
                    }
                }).check();

        btn_open_agenda = (Button)findViewById(R.id.btn_open_agenda);
//        btn_open_storage = (Button)findViewById(R.id.btn_open_storage);
        btn_open_assets_one = (Button)findViewById(R.id.btn_open_assets_one);
        btn_open_assets_two = (Button)findViewById(R.id.btn_open_assets_two);
        btn_open_assets_three = (Button)findViewById(R.id.btn_open_assets_three);

        btn_open_agenda.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent (MainActivity.this, ViewActivity.class);
               intent.putExtra("ViewType", "agenda");
               startActivity(intent);
           }
        });

        btn_open_assets_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, ViewActivity.class);
                intent.putExtra("ViewType", "assets_one");
                startActivity(intent);
            }
        });

        btn_open_assets_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, ViewActivity.class);
                intent.putExtra("ViewType", "assets_two");
                startActivity(intent);
            }
        });

        btn_open_assets_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, ViewActivity.class);
                intent.putExtra("ViewType", "assets_three");
                startActivity(intent);
            }
        });

//        btn_open_storage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent browserPDF = new Intent(Intent.ACTION_GET_CONTENT);
//                browserPDF.setType("application/pdf");
//                browserPDF.addCategory(Intent.CATEGORY_OPENABLE);
//                startActivityForResult(Intent.createChooser(browserPDF, "Select PDF"), PICK_PDF_CODE);
//            }
//        });
    }

    //Ctrl+O

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedPDF = data.getData();
            Intent intent = new Intent (MainActivity.this, ViewActivity.class);
            intent.putExtra("ViewType", "storage");
            intent.putExtra("FileUri", selectedPDF.toString());
            startActivity(intent);
        }
    }
}
