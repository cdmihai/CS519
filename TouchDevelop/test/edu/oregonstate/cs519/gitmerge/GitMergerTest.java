package edu.oregonstate.cs519.gitmerge;

import static org.junit.Assert.*;

import org.eclipse.jgit.api.MergeResult;
import org.junit.Test;

public class GitMergerTest {
	
	@Test
	public void testMergeNoConflict() {
		MergeResult result = GitMerger.merge("aaaa\nbbbbb\nccccc", "aaxa\nbbbbb\nccccc", "aaaa\nbbbbb\ncccxc");
		assertEquals(MergeResult.MergeStatus.MERGED, result.getMergeStatus());
	}
	
	@Test
	public void testMergeConflict() {
		MergeResult result = GitMerger.merge("aaaa", "aaxa", "axaa");
		assertEquals(MergeResult.MergeStatus.CONFLICTING, result.getMergeStatus());
	}

}