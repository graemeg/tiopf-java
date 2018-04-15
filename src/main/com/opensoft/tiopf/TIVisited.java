package com.opensoft.tiopf;

public class TIVisited extends TIBaseObject {

	public TIVisited() {
		// TODO Auto-generated constructor stub
	}

	public void Iterate(TIVisitor aVisitor) {
		aVisitor.execute(this);
	}

	public void executeVisitor(TIVisitor visitor, TITouchedByVisitor touchedByVisitorList) {
		// TODO Implement me - executeVisitor()
	}

	public void iterateAssignTouched(TIVisitor visitor, TITouchedByVisitorList touchedByVisitorList) {
		// TODO Auto-generated method stub
		switch (visitor.getIterationStyle()) {
			case isBottomUpSinglePass:
				iterateBottomUpSinglePass(visitor, touchedByVisitorList);
				break;
			case isTopDownRecurse:
				iterateTopDownRecurse(visitor, touchedByVisitorList);
				break;
			case isTopDownSinglePass:
				iterateTopDownSinglePass(visitor, touchedByVisitorList);
				break;
			default:
				throw new RuntimeException("Unknown visitor.iterationStyle detected.");
		}
	}

	protected void touchMethodExecuteVisitor(TIVisited visited, TIVisitor visitor, TITouchedByVisitorList touchedByVisitorList, int iterationDepth) {
		TITouchedByVisitor lVisitedCandidate = new TITouchedByVisitor(visitor, visited, iterationDepth);
		touchedByVisitorList.add(lVisitedCandidate);
		executeVisitor(visitor, lVisitedCandidate);

	}

	private void iterateTopDownSinglePass(TIVisitor visitor, TITouchedByVisitorList touchedByVisitorList) {
		// TODO Implement me

	}

	private void iterateBottomUpSinglePass(TIVisitor visitor, TITouchedByVisitorList touchedByVisitorList) {
		// TODO Implement me

	}

	private void iterateTopDownRecurse(TIVisitor visitor, TITouchedByVisitorList touchedByVisitorList) {
		// TODO Auto-generated method stub
		TITouchedByVisitorList list = new TITouchedByVisitorList();
		try {
			iterateRecursive(visitor, null, list, touchMethodExecuteVisitor, 0);
		} finally {

		}
	}

}
