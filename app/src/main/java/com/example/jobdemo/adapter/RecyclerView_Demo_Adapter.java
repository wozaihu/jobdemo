package com.example.jobdemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.jobdemo.MyApplication;
import com.example.jobdemo.R;
import com.example.jobdemo.bean.ImageSizeBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecyclerView_Demo_Adapter extends RecyclerView.Adapter<RecyclerView_Demo_Adapter.MyViewHolder> {
    private static final String TAG = "RecyclerView_Demo_Adapt";
    private Context context;
    private final String[] stringArray;
    private List<Float> scales = new ArrayList();
    private HashMap<Integer, ImageSizeBean> imageSizeBean = new HashMap<>();

    public void cleanImageSizeBean() {
        imageSizeBean.clear();
        imageSizeBean = null;
    }

    public RecyclerView_Demo_Adapter() {
        context = MyApplication.getAppContent();
        stringArray = context.getResources().getStringArray(R.array.pictureUrls);
//        setImageScale();
//        Log.d(TAG, "RecyclerView_Demo_Adapter: 初始化");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itme_recycler_dame, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        final ViewGroup.LayoutParams layoutParams = holder.iv_picture.getLayoutParams();
//        layoutParams.width = MeasureUtils.getScreenWidth(context) / 2;
//        layoutParams.height = (int) (layoutParams.width / scales.get(position));
//        holder.iv_picture.setLayoutParams(layoutParams);
//        Log.d(TAG, "集合里有几个数: " + imageSizeBean.size() + "是否包含:" + position + "----" + imageSizeBean.containsKey(position));
//
//        Glide.with(context).asBitmap().load(stringArray[position]).into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        Log.d(TAG, "原始图片宽高: " + resource.getWidth() + "-------" + resource.getHeight());
//                        //这个bitmap就是你图片url加载得到的结果
//                        //获取bitmap信息，可赋值给外部变量操作，也可在此时行操作。
//                        if (!imageSizeBean.containsKey(position)) {
//                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.iv_picture.getLayoutParams();//获取你要填充图片的布局的layoutParam
//                            int realityHeight = (int) (((float) resource.getHeight()) / resource.getWidth() * MeasureUtils.getScreenWidth(context) / 2);
//                            layoutParams.height = realityHeight;
//                            //因为是2列,所以宽度是屏幕的一半,高度是根据bitmap的高/宽*屏幕宽的一半
//                            int realityWidth = MeasureUtils.getScreenWidth(context) / 2;//这个是布局的宽度
//                            layoutParams.width = realityWidth;
//                            holder.iv_picture.setLayoutParams(layoutParams);//容器的宽高设置好了
//                            resource = zoomImg(resource, layoutParams.width, layoutParams.height);
//                            ImageSizeBean bean = new ImageSizeBean();
//                            bean.setHeight(realityHeight);
//                            bean.setWidth(realityWidth);
////                            bean.setBitmap(resource);
//                            imageSizeBean.put(position, bean);
//                            // 然后在改变一下bitmap的宽高
//                            Log.d(TAG, "添加了缓存: "+bean.toString());
//                            holder.iv_picture.setImageBitmap(resource);
//                        }else {
//                            Log.d(TAG, "使用缓存的图片: " + position);
//                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.iv_picture.getLayoutParams();//获取你要填充图片的布局的layoutParam
//                            layoutParams.height = imageSizeBean.get(position).getHeight();
//                            //因为是2列,所以宽度是屏幕的一半,高度是根据bitmap的高/宽*屏幕宽的一半
//                            layoutParams.width = imageSizeBean.get(position).getWidth();//这个是布局的宽度
//                            holder.iv_picture.setLayoutParams(layoutParams);//容器的宽高设置好了
//                            holder.iv_picture.setImageBitmap(zoomImg(resource, layoutParams.width, layoutParams.height));
//                        }
//                    }
//                });


//        使用一个kotlin的工具类获得图片宽高，并缓存，为了解决上拉图片重绘宽高的问题
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.iv_picture.getLayoutParams();//获取你要填充图片的布局的layoutParam
    }

    @Override
    public int getItemCount() {
        return stringArray.length;
    }


    private void setImageScale() {
        Log.d(TAG, "去请求规格尺寸: ");
        for (int i = 0; i < stringArray.length; i++) {
            Glide.with(context).load(stringArray[i]).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    float scale = resource.getIntrinsicWidth() / (float) resource.getIntrinsicHeight();
                    scales.add(scale);
                    Log.d(TAG, "onResourceReady: " + scale);
                    notifyDataSetChanged();
                }
            });
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_picture;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_picture = itemView.findViewById(R.id.iv_picture_recycler_dame);
        }
    }


    //改变bitmap尺寸的方法
    public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }

    //改变bitmap尺寸的方法
    public static Bitmap zoomImg(Drawable bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        BitmapDrawable bitmapDrawable = (BitmapDrawable) bm;
        Bitmap bitmap = bitmapDrawable.getBitmap();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newbm;
    }
}
