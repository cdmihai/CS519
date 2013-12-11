package edu.oregonstate.cs519.touchdevelop.main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.oregonstate.cs519.touchdevelop.Script;

public class TDTeamSuccessorFinder implements Serializable{

	private static final long serialVersionUID = -5529402601767245206L;

	private static final Pattern p = Pattern.compile("\"scriptId\" ?: ?\"(.*)\"");;
	private static final String PATH_TO_EDITS = "../microedits/osu";

	private Set<String> scriptIds = new HashSet<>();
	private AtomicInteger filesVisited = new AtomicInteger(0);

	private Map<String, List<String>> successors;
	private Map<String, Set<String>> scriptToFile = new HashMap<>();

	private void populateScriptIds() throws IOException {
		Files.walkFileTree(Paths.get(PATH_TO_EDITS), new FileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

				String content = new String(Files.readAllBytes(file));
				Set<String> foundScriptIDs = getScriptIDsFromFile(content);

				scriptIds.addAll(foundScriptIDs);

				for (String scriptId : foundScriptIDs) {
					Set<String> files = scriptToFile.get(scriptId);

					if (files == null) {
						files = new HashSet<>();
						scriptToFile.put(scriptId, files);
					}

					files.add(file.toString());
				}

				filesVisited.incrementAndGet();

				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				// TODO Auto-generated method stub
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				// TODO Auto-generated method stub
				return FileVisitResult.CONTINUE;
			}
		});
	}

	protected Set<String> getScriptIDsFromFile(String scriptData) {
		Set<String> scriptIds = new HashSet<>();
		Matcher m = p.matcher(scriptData);

		while (m.find()) {
			scriptIds.add(m.group(1));
		}

		return scriptIds;
	}

	protected Map<String, List<String>> findSuccessors(Set<String> scriptIds) {

		Map<String, List<String>> rootsWithMoreThanTwoSuccesors = new HashMap<>();

		for (String potentialRootScript : scriptIds) {
			List<String> successors = new ArrayList<>();

			for (Script successor : new Script(potentialRootScript).getSuccessors())
				if (scriptIds.contains(successor.getID()))
					successors.add(successor.getID());

			if (successors.size() > 1)
				rootsWithMoreThanTwoSuccesors.put(potentialRootScript, successors);
		}

		return rootsWithMoreThanTwoSuccesors;
	}

	private void findSuccessors() {
		successors = findSuccessors(scriptIds);
	}

	public Map<String, List<String>> getSuccessorMapping() {
		return successors;
	}

	public Map<String, Set<String>> getScriptToFileMapping() {
		return scriptToFile;
	}

	private static void writeObjectToFile(Object finder) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bout);

		oos.writeObject(finder);

		Files.write(Paths.get("../results/TDSUCC"), bout.toByteArray(), StandardOpenOption.CREATE);
	}

	public static void main(String[] args) throws IOException {
		TDTeamSuccessorFinder finder = new TDTeamSuccessorFinder();

		finder.populateScriptIds();
		finder.findSuccessors();

		System.out.println(finder.getSuccessorMapping().keySet().size());
		System.out.println(finder.getSuccessorMapping());
		System.out.println(finder.getScriptToFileMapping());

		writeObjectToFile(finder);
	}
}
