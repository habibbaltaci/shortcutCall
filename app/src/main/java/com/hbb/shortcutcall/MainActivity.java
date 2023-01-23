package com.hbb.shortcutcall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
private EditText et_phoneNumber;
private ImageButton ibtn_call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_phoneNumber=(EditText) findViewById(R.id.et_phoneNumber);
        ibtn_call=(ImageButton) findViewById(R.id.ibtn_call);

    }

/*
    private void callDialog()
    {
        ViewGroup viewGroup = findViewById(android.R.id.content);

        View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, viewGroup, false);

        TextView tvDesc=dialogView.findViewById(R.id.tvDialogAlert);
        tvDesc.setText(objDemand.getGSM());

        TextView tvHead=dialogView.findViewById(R.id.tvDialogAlertHead);
        tvHead.setText(objDemand.getTalepSahibi());

        RelativeLayout alertHead=dialogView.findViewById(R.id.rl_customDialog);
        alertHead.setBackgroundColor(getColor(R.color.Orange));

        Button Ok = dialogView.findViewById(R.id.buttonOk);

        Ok.setText(getString(R.string.ara));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        Button btnCancel = dialogView.findViewById(R.id.buttonCancel);
        btnCancel.setVisibility(View.VISIBLE);
        builder.setView(dialogView);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Ok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                alertDialog.dismiss();
            }
        });
    } //Arama kısayolu customAlertDialog*/

    public void btn_ibtn_call_Click(View view)
    {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse( (et_phoneNumber.getText().toString().trim().length()==9?"tel:05":"tel:0")+et_phoneNumber.getText().toString().trim()));

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Yazılıma izin vermelisiniz!")
                    .setCancelable(false)
                    .setPositiveButton("Çıkış", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            return;
                        }
                    });

            android.app.AlertDialog alert = alertDialogBuilder.create();
            alert.show();
            return;
        }
        startActivity(callIntent);
    }
}