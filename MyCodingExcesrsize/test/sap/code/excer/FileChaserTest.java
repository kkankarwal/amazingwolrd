package sap.code.excer;

import static org.junit.Assert.*;

import org.junit.Test;

public class FileChaserTest {

	FileChaser chaser = new FileChaser();
	
	@Test
	public void testWalkOverDirectory() {
		String directoryPath = "C://MyFolder//";
		String regex = "*MFile*";
		long timeout = 2;
		
		chaser.walkOverDirectory(directoryPath, regex, timeout);
	}

}
