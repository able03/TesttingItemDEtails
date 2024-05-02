package com.example.testtingitemdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.Objects;

public class ViewPager2Adapter extends PagerAdapter
{
    private Context context;
    private int[] images;
    private LayoutInflater inflater;

    public ViewPager2Adapter(Context context, int[] images)
    {
        this.context = context;
        this.images = images;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == ((FrameLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        View view = inflater.inflate(R.layout.image_slider_layout, container, false);
        ImageView iv = view.findViewById(R.id.ivImageSlider);
        iv.setImageResource(images[position]);
        Objects.requireNonNull(container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        container.removeView((FrameLayout) object);
    }
}
