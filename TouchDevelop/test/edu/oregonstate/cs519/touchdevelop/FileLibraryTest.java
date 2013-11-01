package edu.oregonstate.cs519.touchdevelop;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileLibraryTest {
	
	private FileLibrary fileDumper;
	private String testFolder = "test-files";

	@Before
	public void setUp() {
		fileDumper = new FileLibrary(testFolder);
	}
	
	@After
	public void tearDown() {
		try {
			Files.walkFileTree(Paths.get(testFolder), new FileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir,
						BasicFileAttributes attrs) throws IOException {
					// TODO Auto-generated method stub
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file,
						BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file,
						IOException exc) throws IOException {
					// TODO Auto-generated method stub
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir,
						IOException exc) throws IOException {
					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
		}
	}
	
	@Test
	public void testDumpScript() throws Exception {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put(Script.ID,"aaaa");
		hashMap.put(Script.NAME,"test");
		hashMap.put(Script.USER_ID,"test_usr");
		Script script = new Script(hashMap );
		fileDumper.writeScript(script);
		byte[] fileBytes = Files.readAllBytes(Paths.get(testFolder+"/aaaa"));
		String contents = new String(fileBytes);
		assertEquals("{\"id\":\"aaaa\",\"name\":\"test\",\"userid\":\"test_usr\"}",contents);
	}
	
	@Test
	public void testGetScript() throws Exception {
		Files.write(Paths.get(testFolder+"/test"), "{\"name\":\"test\",\"userid\":\"test_usr\"}".getBytes(), StandardOpenOption.CREATE);
		Script script = fileDumper.getScript("test");
		assertEquals("test", script.getName());
		assertEquals("test_usr", script.getUserID());
	}

}
