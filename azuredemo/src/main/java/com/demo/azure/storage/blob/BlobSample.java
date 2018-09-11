package com.demo.azure.storage.blob;

import java.io.*;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;

public class BlobSample {
	public static final String storageConnectionString = "DefaultEndpointsProtocol=http;"
			+ "AccountName=your_account_name;" + "AccountKey= your_account_key";

	public static void main(String[] args) {
		try {
			CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionString);
			CloudBlobClient serviceClient = account.createCloudBlobClient();

			// Container name must be lower case.
			CloudBlobContainer container = serviceClient.getContainerReference("myimages");
			container.createIfNotExists();

			// Upload an image file.
			CloudBlockBlob blob = container.getBlockBlobReference("image1.jpg");
			File sourceFile = new File("c:\\myimages\\image1.jpg");
			blob.upload(new FileInputStream(sourceFile), sourceFile.length());

			// Download the image file.
			File destinationFile = new File(sourceFile.getParentFile(), "image1Download.tmp");
			blob.downloadToFile(destinationFile.getAbsolutePath());
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.print("FileNotFoundException encountered: ");
			System.out.println(fileNotFoundException.getMessage());
			System.exit(-1);
		} catch (StorageException storageException) {
			System.out.print("StorageException encountered: ");
			System.out.println(storageException.getMessage());
			System.exit(-1);
		} catch (Exception e) {
			System.out.print("Exception encountered: ");
			System.out.println(e.getMessage());
			System.exit(-1);
		}
	}
}