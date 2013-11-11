package edu.oregonstate.cs519.gitmerge;

import java.io.File;
import java.io.PrintWriter;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;

/**
 * This class merges two branches using the Git default algorithm.
 * 
 * @author caius
 *
 */
public class GitMerger {
	
	private static final String MASTER_BRANCH = "master";
	private static final String B_BRANCH = "b";
	private static final String A_BRANCH = "a";
	private static final String TEST_FILE = "test_file.txt";
	private static final String REPO_LOCATION = "/Volumes/RAM Disk/repos/";
	

	public static MergeResult merge(String base, String a, String b){
		PersonIdent author = new PersonIdent("TestUser", "test.user@example.com");
		
		File gitDir = new File(REPO_LOCATION + System.nanoTime());
		try {
			gitDir.mkdir();
			Git repo = Git.init().setDirectory(gitDir).setBare(false).call();
			File file = new File(gitDir, TEST_FILE);
			if (!file.exists())
				file.createNewFile();
			
			String baseCommitMsg = "first";
			commit(base, author, repo, file, baseCommitMsg);
			
			createAndCheckoutBranch(repo, A_BRANCH);
			RevCommit aCommit = commit(a, author, repo, file, "second");
			repo.checkout().setName(MASTER_BRANCH).call();
			
			createAndCheckoutBranch(repo, B_BRANCH);
			commit(b, author, repo, file, "third");
			
			MergeResult result = repo.merge().include(aCommit).call();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			deleteDirectory(gitDir);
		}
		return null;
	}

	public static void createAndCheckoutBranch(Git repo, String branchName)
			throws GitAPIException, RefAlreadyExistsException,
			RefNotFoundException, InvalidRefNameException,
			CheckoutConflictException {
		repo.branchCreate().setName(branchName).call();
		repo.checkout().setName(branchName).call();
	}

	private static RevCommit commit(String content, PersonIdent author, Git repo,
			File file, String commitMsg) throws Exception {
		PrintWriter writer = new PrintWriter(file);
		writer.write(content);
		writer.close();
		repo.add().addFilepattern(TEST_FILE).call();
		return repo.commit().setAuthor(author).setMessage(commitMsg).call();
	}
	
	public static boolean deleteDirectory(File path) {
	    if (path.exists()) {
	        File[] files = path.listFiles();
	        for (int i = 0; i < files.length; i++) {
	            if (files[i].isDirectory()) {
	                deleteDirectory(files[i]);
	            } else {
	                files[i].delete();
	            }
	        }
	    }
	    return (path.delete());
	}
}
