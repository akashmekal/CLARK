package com.automationpractice.core.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class FileUtility {

	public FileInputStream getFileInputStream(String filepath) {
		FileInputStream fileInputStream = null;
		try{
			fileInputStream = new FileInputStream(new File(filepath));
		}
		catch(FileNotFoundException eFileNotFound) {
			System.out.println("File with specified path doesn't exists. Filepath = " + filepath);
			eFileNotFound.printStackTrace();
		}
		return fileInputStream;
	}

	/**
	 * @Function  This method is designed to get the FileOutputStream of the specified filepath
	 */
	public FileOutputStream getFileOutputStream(String filepath) {
		FileOutputStream fileOutputStream = null;
		try{
			fileOutputStream = new FileOutputStream(new File(filepath));
		}
		catch(FileNotFoundException eFileNotFound) {
			System.out.println("File with specified path doesn't exists. Filepath = " + filepath);
			eFileNotFound.printStackTrace();
		}
		return fileOutputStream;
	}

	/**
	 * @Function  This method is designed to create a copy of the source file to the destination file location
	 */
	public File createFileCopy(String sourceFilepath, String destinationFilepath) {
		File destinationFile = new File(destinationFilepath);
		if(! destinationFile.exists()) {
			try {
				FileUtils.copyFile( new File(sourceFilepath), destinationFile);
				System.out.println("Copied File with Filepath " + destinationFile.getAbsolutePath());
			} catch(IOException eIOException) {
				eIOException.printStackTrace();
			}	
		}
		return destinationFile;
	}

	/**
	 * @Function  This method is designed to close the provided FileInputStream object
	 */
	public void closeFileInputStream(FileInputStream fileInputStream) {
		try {
			fileInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @Function  This method is designed to close the provided FileOutputStream object
	 */
	public void closeFileOutputStream(FileOutputStream fileOutputStream) {
		try {
			fileOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
