package edu.oregonstate.cs519.touchdevelop;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public final class TestUtils {

	public final static String TEST_FOLDER = "test-files";

	public static void deleteTestFiles() throws IOException {
		Files.walkFileTree(Paths.get(TEST_FOLDER),
				new FileVisitor<Path>() {
	
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
	}

}
