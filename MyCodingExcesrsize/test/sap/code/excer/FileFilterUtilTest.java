package sap.code.excer;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class FileFilterUtilTest {

	@Test
	public void testIsFileMatchTargetFilePattern() {
		boolean x = FileFilterUtil.isFileMatchTargetFilePattern(new File("C://MyFolder//"), "*");
		assertTrue("Matcher should return True !", x);
	}
	
	@Test
	public void testIsFileMatchTargetFilePatternForNotMacher() {
		boolean x = FileFilterUtil.isFileMatchTargetFilePattern(new File("C://MyFolder//"), "*MFile*");
		assertTrue("Matcher should return True !", !x);
	}

	@Test
	public void testIsFileMatchTargetFilePattern_2() {
		boolean x = FileFilterUtil.isFileMatchTargetFilePattern(new File("C://MyFolder//UMFile123.txt"), "*MFile*");
		assertTrue("Matcher should return True !", x);
	}
}
