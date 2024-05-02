package com.example.testtingitemdetails;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class ClipboardActivity extends AppCompatActivity
{

    private EditText et_copy_field;
    private TextView tv_paste;
    private MaterialButton btn_copy, btn_paste;

    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clipboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initValues();
        setLisneters();
    }

    private void initValues()
    {
        et_copy_field = findViewById(R.id.etTextToCopy);

        tv_paste = findViewById(R.id.tvPaste);

        btn_copy = findViewById(R.id.btnCopy);
        btn_paste = findViewById(R.id.btnPaste);

        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    }

    private void setLisneters()
    {
        btn_copy.setOnClickListener(copy -> {
            String text = et_copy_field.getText().toString();
            myClip = ClipData.newPlainText("text", text);
            myClipboard.setPrimaryClip(myClip);
        });

        btn_paste.setOnClickListener(paste -> {
            ClipData abc = myClipboard.getPrimaryClip();
            ClipData.Item item = abc.getItemAt(0);
            String text = item.getText().toString();
            tv_paste.setText(text);
        });
    }


}