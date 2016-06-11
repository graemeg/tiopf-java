package com.opensoftltd.tiopf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
		lVisitor.execute(lVisited);
		assertTrue(lVisitor.getVisited().equals(lVisited));
	}

	@Test
	public void acceptVisitorDefaultsToTrue() {
		TIVisitor lVisitor = new TIVisitor();
		assertEquals(true, lVisitor.acceptVisitor());
	}

}
