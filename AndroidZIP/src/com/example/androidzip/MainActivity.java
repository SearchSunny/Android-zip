package com.example.androidzip;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.zip.ZipException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.androidzip.tools.DirTraversal;
import com.example.androidzip.tools.ZipUtils;
/**
 * Android zip文件压缩解压缩
 * @author miaowei
 *
 */
public class MainActivity extends Activity {

	/**
	 * 压缩
	 */
	private Button btn_zip;
	/**
	 * 解压
	 */
	private Button btn_unzip;
	
	String pathString = Environment.getExternalStorageDirectory().getAbsolutePath();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_unzip = (Button)findViewById(R.id.btn_unzip);
		
		btn_zip = (Button)findViewById(R.id.btn_zip);
		btn_zip.setOnClickListener(onClickListener);
		btn_unzip.setOnClickListener(onClickListener);
	}
	
	
	private OnClickListener onClickListener = new OnClickListener(){
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_zip:

				try {
					//测试数据，注意更换目录
					LinkedList<File> files = DirTraversal.listLinkedFiles(pathString+"/Android/data/com.mapbar.info.collection/files/cache");
					
					File file = DirTraversal.getFilePath(pathString+"/Android/data/com.mapbar.info.collection/files/", "cache.zip");
					
					ZipUtils.zipFiles(files, file);
					
					//第二种实现
					//ZipUtils.zip(pathString+"/Android/data/com.mapbar.info.collection/files/cache", pathString+"/Android/data/com.mapbar.info.collection/files/cache.zip");
					//ZipUtils.unzip(pathString+"/Android/data/com.mapbar.info.collection/files/cache.zip", pathString+"/Android/data/com.mapbar.info.collection/files/cache");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case R.id.btn_unzip:
				File file = DirTraversal.getFilePath(pathString+"/Android/data/com.mapbar.info.collection/files/", "cache.zip");
				try {
					ZipUtils.upZipFile(file, pathString+"/Android/data/com.mapbar.info.collection/files/cachezip");
				} catch (ZipException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		
		
		}
		
	};
	
}
