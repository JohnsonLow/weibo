package cn.edu.lyw.tiny.model;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * @description 微博图片
 * @version 1.0
 * 
 */
public class WeiboImage {

	/** ImageView 控件 */
	public ImageView imageView;// control

	/** 图片的URL地址 */
	public String imageurl;// url

	/** Bitmap信息 */
	public Bitmap bitmap;// bitmap

	/**
	 * 构造器
	 * 
	 * @param imageView
	 *            ImageView 控件
	 * @param imageurl
	 *            图片的URL地址
	 * @param bitmap
	 *            Bitmap信息
	 */
	public WeiboImage(ImageView imageView, String imageurl, Bitmap bitmap) {
		this.imageView = imageView;
		this.imageurl = imageurl;
		this.bitmap = bitmap;
	}

}
