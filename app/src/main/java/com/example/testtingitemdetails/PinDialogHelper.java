package com.example.testtingitemdetails;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class PinDialogHelper
{
    private EditText et1, et2, et3, et4;
    private Context context;
    private String pinInput;
    private View view;
    private boolean isTrue;
    private String str;

    public PinDialogHelper(Context context)
    {
        this.context = context;
    }
    public PinDialogHelper(){}

    public boolean showDialog()
    {
        initValues();

        et1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.length() == 1)
                {
                    et2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        et2.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.length() == 1)
                {
                    et3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        et3.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.length() == 1)
                {
                    et4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });





        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setTitle("Test PIN")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        str = et1.getText().toString()+et2.getText().toString()+
                                et3.getText().toString()+et4.getText().toString();

                        if(str.equalsIgnoreCase("1234"))
                        {
                            isTrue = true;
                            Toast.makeText(context, "Matched: " + str, Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(context, "Not matched: " + str, Toast.LENGTH_SHORT).show();
                            isTrue = false;
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });

        builder.show();
        return isTrue;
    }

    public String getPinInput()
    {
        return str;
    }


    private void initValues()
    {
        view = LayoutInflater.from(context).inflate(R.layout.custom_dialog_layout, null);

        et1 = view.findViewById(R.id.et1);
        et2 = view.findViewById(R.id.et2);
        et3 = view.findViewById(R.id.et3);
        et4 = view.findViewById(R.id.et4);
    }


}
