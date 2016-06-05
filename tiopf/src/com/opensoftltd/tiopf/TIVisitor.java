package com.opensoftltd.tiopf;

public class TIVisitor extends TIBaseObject {

	private boolean continueVisiting;
	private int depth;
	private IterationStyle iterationStyle;
	private TIVisited visited;

	public TIVisitor() {		
		continueVisiting = true;
		setDepth(0);
		setIterationStyle(IterationStyle.isTopDownRecurse);
	}
	
	public void Execute(TIVisited aVisited) {
		setVisited(aVisited);
	}

	public IterationStyle getIterationStyle() {
		return iterationStyle;
	}

	public void setIterationStyle(IterationStyle iterationStyle) {
		this.iterationStyle = iterationStyle;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public TIVisited getVisited() {
		return visited;
	}

	public void setVisited(TIVisited visited) {
		this.visited = visited;
	}

}