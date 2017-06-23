package com.pengdun.lib_imageloader;

/**
 * <b>Project:</b> YuantaiApplication<br>
 * <b>Create Date:</b> 2016/9/26<br>
 * <b>Author:</b> pengdun<br>
 * <b>Description:</b> <br>
 */
public interface IImageLoader {

    /**
     *  初始化
     * @param builder
     */
    void init(ImageLoader.InnerBuilder builder);

    /**
     *  加载图片
     */
    void load();


}
