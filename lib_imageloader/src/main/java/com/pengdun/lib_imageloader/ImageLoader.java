package com.pengdun.lib_imageloader;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;

/**
 * <b>Project:</b> YuantaiApplication<br>
 * <b>Create Date:</b> 2016/9/26<br>
 * <b>Author:</b> pengdun<br>
 * <b>Description:</b> <br>
 *
 *     图片加载的包装类,实际内部使用 Glide
 */
public class ImageLoader {

    private static Builder sDefaultBuilder;
    private InnerBuilder mBuilder;
    private IImageLoader mImageLoader;

    private ImageLoader(InnerBuilder builder) {
        this.mBuilder = builder;
        if (null != builder.getImageLoader()) {
            this.mImageLoader = builder.getImageLoader();
        } else {
            this.mImageLoader = new DefaultImageLoaderImpl();
        }
    }

    public void load() {
        mImageLoader.init(mBuilder);
        mImageLoader.load();
    }

    public static void defaultBuilder(Builder builder) {
        ImageLoader.sDefaultBuilder = builder;
    }

    public static class Builder {
        private IImageLoader imageLoader;
        private Activity withActivity;
        private Fragment withfragment;
        private Context withContext;
        private View view;
        private String url;
        private int loading;
        private int error;
        private int[] size;
        private OnLoadCallback callback;

        public Builder() {
            this(sDefaultBuilder);
        }

        private Builder(final Builder builder) {
            if (null != builder) {
                this.imageLoader = builder.imageLoader;
                this.withActivity = builder.withActivity;
                this.withfragment = builder.withfragment;
                this.view = builder.view;
                this.url = builder.url;
                this.loading = builder.loading;
                this.error = builder.error;
                this.size = builder.size;
                this.callback = builder.callback;
            }
        }

        public ImageLoader build() {
            return new ImageLoader(new InnerBuilder(this));
        }


        public Builder imageLoader(IImageLoader loader) {
            this.imageLoader = loader;
            return this;
        }


        public Builder with(Activity activity) {
            this.withActivity = activity;
            return this;
        }


        public Builder with(Fragment fragment) {
            this.withfragment = fragment;
            return this;
        }


        public Builder with(Context context) {
            this.withContext = context;
            return this;
        }


        public Builder view(View view) {
            this.view = view;
            return this;
        }


        public Builder url(String url) {
            this.url = url;
            return this;
        }


        public Builder loading(int loading) {
            this.loading = loading;
            return this;
        }


        public Builder error(int error) {
            this.error = error;
            return this;
        }


        public Builder size(int width, int height) {
            this.size = new int[2];
            size[0] = width;
            size[1] = height;
            return this;
        }


        public Builder callback(OnLoadCallback callback) {
            this.callback = callback;
            return this;
        }
    }

    public static class InnerBuilder {
        private Builder builder;

        public InnerBuilder(Builder builder) {
            this.builder = builder;
        }

        public IImageLoader getImageLoader() {
            return builder.imageLoader;
        }

        public Activity getActivity() {
            return builder.withActivity;
        }

        public Fragment getFragment() {
            return builder.withfragment;
        }

        public Context getContext() {
            return builder.withContext;
        }

        public View getView() {
            return builder.view;
        }

        public String getUrl() {
            return builder.url;
        }

        public int getLoading() {
            return builder.loading;
        }

        public int getError() {
            return builder.error;
        }

        public int[] getSize() {
            return builder.size;
        }

        public OnLoadCallback getCallback() {
            return builder.callback;
        }

    }
}
