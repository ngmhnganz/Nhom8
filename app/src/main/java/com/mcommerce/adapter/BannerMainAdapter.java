package com.mcommerce.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.mcommerce.model.BannerMainModel;
import com.mcommerce.nhom8.R;

import java.util.List;

public class BannerMainAdapter extends PagerAdapter {

    Activity context;
    List<BannerMainModel> bannerMainModelList;

    public BannerMainAdapter(Context context, List<BannerMainModel> bannerMainModelList) {
        this.context = (Activity) context;
        this.bannerMainModelList = bannerMainModelList;
    }

    @Override
    public int getCount() {
        return bannerMainModelList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.banner_main_layout,null);

        ImageView imageView = view.findViewById(R.id.imv_LyBannerMain);
        imageView.setClipToOutline(true);
        imageView.setImageResource(bannerMainModelList.get(position).getImvBanner());
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
