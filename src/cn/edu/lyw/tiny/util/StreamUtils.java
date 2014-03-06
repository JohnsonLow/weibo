package cn.edu.lyw.tiny.util;

import java.io.Closeable;
import java.io.IOException;

import android.util.Log;

public class StreamUtils {
	private static final String TAG = "STREAM_ERROR";
	public static void closeStream(Closeable stream){
		if(stream != null){
			try {
				stream.close();
			} catch (IOException e) {
				Log.e(TAG,"关闭流失败");
			}
		}
	}
}
