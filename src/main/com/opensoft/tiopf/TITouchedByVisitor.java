package com.opensoft.tiopf;

/**
 * A container for holding Visitor/Visited pairs. TITouchedByVisitor is used by TIObjectVisitor(s) when Final must be
 * executed against each TIVisited that was touched by the iteration cycle.
 *
 * @author Graeme Geldenhuys
 *
 */
public class TITouchedByVisitor extends TIBaseObject {

	private TIVisitor visitor;
	private TIVisited visited;
	private int iterationDepth;

	public TITouchedByVisitor(TIVisitor visitor, TIVisited visited, int iterationDepth) {
		this.visitor = visitor;
		this.visited = visited;
		this.iterationDepth = iterationDepth;
	}

	public TIVisitor getVisitor() {
		return visitor;
	}

	public TIVisited getVisited() {
		return visited;
	}

	public int getIterationDepth() {
		return iterationDepth;
	}

}
