package com.example.androidzip.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 文件夹遍历
 * @author miaowei
 *
 */
public class DirTraversal {

	//no recursion
    public static LinkedList<File> listLinkedFiles(String strPath) {
        LinkedList<File> list = new LinkedList<File>();
        File dir = new File(strPath);
        File file[] = dir.listFiles();
        for (int i = 0; i < file.length; i++) {
            /*if (file[i].isDirectory()){
            	
            	list.add(file[i]);
            }else{
            	
            	System.out.println(file[i].getAbsolutePath());
            	
            }*/
        	list.add(file[i]); 
        }
        /*File tmp;
        while (!list.isEmpty()) {
            tmp = (File) list.removeFirst();
            if (tmp.isDirectory()) {
                file = tmp.listFiles();
                if (file == null)
                    continue;
                for (int i = 0; i < file.length; i++) {
                    if (file[i].isDirectory())
                        list.add(file[i]);
                    else
                        System.out.println(file[i].getAbsolutePath());
                }
            } else {
                System.out.println(tmp.getAbsolutePath());
            }
        }*/
        return list;
    }
 
     
    //recursion
    public static ArrayList<File> listFiles(String strPath) {
        return refreshFileList(strPath);
    }
 
    public static ArrayList<File> refreshFileList(String strPath) {
        ArrayList<File> filelist = new ArrayList<File>();
        File dir = new File(strPath);
        File[] files = dir.listFiles();
 
        if (files == null)
            return null;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                refreshFileList(files[i].getAbsolutePath());
            } else {
                if(files[i].getName().toLowerCase().endsWith("zip")){
                	
                	filelist.add(files[i]);
                }
                    
            }
        }
        return filelist;
    }
    
    public static ArrayList<File> arrayListFiles(String strPath){
    	
    	 ArrayList<File> filelist = new ArrayList<File>();
         File dir = new File(strPath);
         File[] files = dir.listFiles();
         for (int i = 0; i < files.length; i++) {
			
        	 filelist.add(files[i].getAbsoluteFile());
		}
         return filelist;
    }
    //-----4.0读取文件的报 open failed: ENOENT (No such file or directory)
    /**
	 * 1\可先创建文件的路径
	 * @param filePath
	 */
	public static void makeRootDirectory(String filePath) {
		File file = null;
		try {
			file = new File(filePath);
			if (!file.exists()) {
				file.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    /**
     * 2\然后在创建文件名就不会在报该错误
     * @param filePath
     * @param fileName
     * @return
     */
	public static File getFilePath(String filePath, String fileName) {
		File file = null;
		makeRootDirectory(filePath);
		try {
			file = new File(filePath + fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}

	
}
