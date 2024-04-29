package com.example.testtingitemdetails;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ChipGroup chip_group;
    private RecyclerView rv;
    private CardAdapter adapter;
    private List<CardModel> cardList;
    private TextView tvName;
    private ImageView ivIcon;
    private int[] images;
    private ViewPager2Adapter viewPager2Adapter;
    private ViewPager viewPager2;
    private MaterialButton button, btnDialog, btn_green, btn_red;
    private boolean isTrue;
    private  PinDialogHelper pinDialogHelper;
    private CustomToast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initValues();
        setCardListData();
        populateRV();
        setChip_group();
        setImageSlider();
        setListener();
    }

    private void initValues()
    {
        chip_group = findViewById(R.id.chipGroup);
        rv = findViewById(R.id.rvMain);
        cardList = new ArrayList<>();
        tvName = findViewById(R.id.tcDetailsName);
        ivIcon = findViewById(R.id.ivDetailsIcon);
        viewPager2 = findViewById(R.id.vp2);
        button = findViewById(R.id.btnShowNotification);
        btnDialog = findViewById(R.id.btnShowCustomDiaolog);
        images  = new int[]{R.drawable.ic_club, R.drawable.ic_diamond,
                R.drawable.ic_heart, R.drawable.ic_spade};
        btn_green = findViewById(R.id.btnGreen);
        btn_red = findViewById(R.id.btnRed);
        toast = new CustomToast();
    }

    private void populateRV()
    {
        adapter = new CardAdapter(this, cardList, cardModel -> {
            tvName.setText(cardModel.getName());
            ivIcon.setImageResource(cardModel.getImgRID());
        });

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
    }

    private static Bundle getData()
    {
        Bundle bundle = new Bundle();
        bundle.putString("name", CardStaticModel.getName());
        bundle.putInt("age", CardStaticModel.getAge());
        return bundle;
    }

    private void setListener()
    {
        button.setOnClickListener(click -> {
            toast.myToast(this, R.drawable.ic_heart, CardStaticModel.getName(), String.valueOf(CardStaticModel.getAge()), getResources().getColor(R.color.white));
            NotificationHelper notificationHelper = new NotificationHelper();
            notificationHelper.showNotification(MainActivity.this, "Test Notif", button.getText().toString());
        });

        btnDialog.setOnClickListener(click -> {
//            showCustomDialog();
            pinDialogHelper = new PinDialogHelper(this);
            isTrue = pinDialogHelper.showDialog();
        });

        btn_green.setOnClickListener(click -> {
            toast.myToast(this, R.drawable.ic_heart, "Success", "green", getResources().getColor(R.color.green_light));
        });

        btn_red.setOnClickListener(click -> {
            toast.myToast(this, R.drawable.ic_heart, "Failed", "red", getResources().getColor(R.color.red_light));
        });
    }

    private void setChip_group()
    {
        chip_group.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener()
        {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup chipGroup, @NonNull List<Integer> list)
            {
               for(int i : list)
               {
                   Chip chip = findViewById(i);
                   filteredList(chip.getText().toString());
               }
            }
        });
    }

    private void filteredList(String category)
    {
        List<CardModel> filteredList = new ArrayList<>();
        for(CardModel card : cardList)
        {
            if(category.equalsIgnoreCase("All"))
            {
                filteredList.add(card);
            }

            if(card.getCategory().equalsIgnoreCase(category))
            {
                filteredList.add(card);
            }
        }

        if(!filteredList.isEmpty())
        {
            adapter.filteredList(filteredList);
        }
    }

    private void setImageSlider()
    {
        viewPager2Adapter = new ViewPager2Adapter(this, images);
        viewPager2.setAdapter(viewPager2Adapter);
    }

    private void showCustomDialog()
    {
        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomDialog)
                .setView(view)
                .setTitle("Custom Dialog")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        PinDialogHelper pinDialogHelper = new PinDialogHelper(MainActivity.this);
                        pinDialogHelper.showDialog();

                        Toast.makeText(MainActivity.this, pinDialogHelper.getPinInput(), Toast.LENGTH_SHORT).show();
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
    }




    private void setCardListData()
    {
        cardList.add(new CardModel(1, R.drawable.ic_spade, "Ace", "Spades"));
        cardList.add(new CardModel(2, R.drawable.ic_spade, "Eight", "Spades"));
        cardList.add(new CardModel(3, R.drawable.ic_spade, "King", "Spades"));

        cardList.add(new CardModel(4, R.drawable.ic_heart, "Queen", "Hearts"));
        cardList.add(new CardModel(5, R.drawable.ic_heart, "Jack", "Hearts"));
        cardList.add(new CardModel(6, R.drawable.ic_heart, "Seven", "Hearts"));

        cardList.add(new CardModel(7, R.drawable.ic_club, "Two", "Clubs"));
        cardList.add(new CardModel(8, R.drawable.ic_club, "King", "Clubs"));
        cardList.add(new CardModel(9, R.drawable.ic_club, "Five", "Clubs"));

        cardList.add(new CardModel(10, R.drawable.ic_diamond, "Ace", "Diamonds"));
        cardList.add(new CardModel(11, R.drawable.ic_diamond, "Four", "Diamonds"));
        cardList.add(new CardModel(12, R.drawable.ic_diamond, "Nine", "Diamonds"));
    }
}