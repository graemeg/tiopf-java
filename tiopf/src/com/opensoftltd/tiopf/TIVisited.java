package com.opensoftltd.tiopf;

public class TIVisited extends TIBaseObject {

	public TIVisited() {
		// TODO Auto-generated constructor stub
	}

	public void Iterate(TIVisitor aVisitor) {
		aVisitor.execute(this);
	}

}
