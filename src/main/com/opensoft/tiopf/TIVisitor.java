package com.opensoft.tiopf;

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

	public void execute(TIVisited aVisited) {
		setVisited(aVisited);
	}

	protected boolean acceptVisitor() {
		return true;
	}

	protected boolean acceptVisitor(TIVisited aVisited) {
		setVisited(aVisited);
		return acceptVisitor();
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
