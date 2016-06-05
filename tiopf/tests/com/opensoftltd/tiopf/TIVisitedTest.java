package com.opensoftltd.tiopf;

import static org.junit.Assert.*;

import org.junit.Test;

public class TIVisitedTest {

	@Test
	public void testIterate() {
		TIVisited lVisited = new TIVisited();
		lVisited.Iterate(new TIVisitor());
	}

}
