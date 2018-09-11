package sap.code.excer;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class FileChaser {

	private Queue<File> fileQueue = new LinkedList<File>();
	static String regex = "*.txt";
	static long timeout = 5; // sec
	static String directoryPath = "C://MyFolder//";

	public static void main(String[] args) {
		new FileChaser().walkOverDirectory(directoryPath, regex, timeout);
	}

	public void walkOverDirectory(String directoryPath, String regex,
			long timeout) {
		System.out.println("Starting directory exploration ...");

		TimerUtil.registerTimer(timeout);
		this.fileQueue.add(new File(directoryPath));

		while (!this.fileQueue.isEmpty()) {
			File element = fileQueue.poll();
			new Thread(new DirectoryExlorerTask(element, regex)).start();
			if (element.isDirectory()) {
				for (File file : element.listFiles()) {
					this.fileQueue.add(file);
				}
			}
		}

	}

}
