package sap.code.excer;

import java.io.File;

public class DirectoryExlorerTask implements Runnable {

	private File baseDir;
	private String regex;

	DirectoryExlorerTask(File directoryPath, String regex) {
		this.baseDir = directoryPath;
		this.regex = regex;
	}

	@Override
	public void run() {
			if (FileFilterUtil.isFileMatchTargetFilePattern(baseDir, regex))
				System.out.println(baseDir.getAbsolutePath());
	}
}
