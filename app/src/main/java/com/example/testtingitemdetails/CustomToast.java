package com.example.testtingitemdetails;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CustomToast extends AppCompatActivity
{

    private ImageView iv_icon;
    private TextView  tv_status, tv_message;
    private CardView cv_main;
    public void myToast(Context context, int imgRID, String status, String message, int bgRID)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);

        iv_icon = view.findViewById(R.id.ivToastIcon);
        tv_status = view.findViewById(R.id.tvToastStatus);
        tv_message = view.findViewById(R.id.tvToastMessage);
        cv_main = view.findViewById(R.id.cvMain);

        iv_icon.setImageResource(imgRID);
        tv_status.setText(status);
        tv_message.setText(message);
        cv_main.setCardBackgroundColor(bgRID);

        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}