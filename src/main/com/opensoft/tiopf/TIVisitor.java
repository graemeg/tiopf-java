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

	protected boolean acceptVisitor() {
		return true;
	}

	protected boolean acceptVisitor(TIVisited aVisited) {
		setVisited(aVisited);
		return acceptVisitor();
	}

	protected TIVisited getVisited() {
		return visited;
	}

	protected void setVisited(TIVisited visited) {
		this.visited = visited;
	}

	protected boolean visitBranch(TIVisited derivedParent, TIVisited visited) {
		return true;
	}

	public void execute(TIVisited aVisited) {
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

	public static Class<? extends TIVisitorController> VisitorControllerClass() {
		return TIVisitorController.class;
	}

}
