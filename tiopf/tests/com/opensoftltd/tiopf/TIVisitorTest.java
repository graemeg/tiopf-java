package com.opensoftltd.tiopf;

import static org.junit.Assert.*;

import org.junit.Test;

public class TIVisitorTest {

	@Test
	public void visitorDepthIsZero() {
		assertEquals(0, new TIVisitor().getDepth());
	}
	
	@Test
	public void visitorDefaultIterationStyle() {
		assertEquals(IterationStyle.isTopDownRecurse, new TIVisitor().getIterationStyle());
	}
	
	@Test
	public void visitorExecuteClassIsTheSame() {
		TIVisited lVisited = new TIVisited();
		TIVisitor lVisitor = new TIVisitor();
		lVisitor.Execute(lVisited);
		assertTrue(lVisitor.getVisited().equals(lVisited));
	}

}
