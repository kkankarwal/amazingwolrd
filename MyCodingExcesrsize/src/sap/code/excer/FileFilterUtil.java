package sap.code.excer;

import java.io.File;

public class FileFilterUtil {
	public static boolean isFileMatchTargetFilePattern(final File f,
			final String targetPattern) {
		String regex = targetPattern.replace(".", "\\.");
		regex = regex.replace("?", ".?").replace("*", ".*");

		//System.out.println(" debug : " + f.getName().matches(regex) + " File " + f + " matches regex " + regex);
		return f.getName().matches(regex);

	}
}