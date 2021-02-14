package com.example.sensometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.content.Intent.ACTION_VIEW;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void OpenWeb(View v) {
        Intent intent = new Intent(ACTION_VIEW, Uri.parse("http://www.sensometer.it")); //purtroppo non esiste ancora
        startActivity(intent);
    }

    public void OpenMail(View v) {
        EditText email_edittext = findViewById(R.id.email_text);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"team.pannia@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, email_edittext.getText().toString());
        startActivity(intent);
    }
}