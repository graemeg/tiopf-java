package com.opensoft.tiopf.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.opensoft.tiopf.TIVisited;
import com.opensoft.tiopf.TIVisitor;

public class TIVisitedTest {

	@Test
	public void testIterate() {
		TIVisited lVisited = new TIVisited();
		TIVisitor lVisitor = new TIVisitor();
		lVisited.Iterate(lVisitor);
		assertTrue(lVisitor.getVisited().equals(lVisited));
	}

}
