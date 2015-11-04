package com.transform.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileWriting {

	public static void main(String[] args) {
		System.out.println(FileWriting.class.getSimpleName());
		DateFormat dateFormat = new SimpleDateFormat("[dd-MM-yyyy HH:mm:ss]");
		Date date = new Date();
		
		System.out.println(dateFormat.format(date));
	}
//		try {
//
//			String content = "This is the content to write into file";
//
//			File file = new File("/users/mkyong/filename.txt");
//
//			// if file doesnt exists, then create it
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//
//			FileWriter fw = new FileWriter(file.getAbsoluteFile());
//			BufferedWriter bw = new BufferedWriter(fw);
//			bw.write(content);
//			bw.close();
//
//			System.out.println("Done");

//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
