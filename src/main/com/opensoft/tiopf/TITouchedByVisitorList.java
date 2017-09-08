package com.opensoft.tiopf;

import java.util.Vector;

/**
 * A list of TtiTouchedByVisitor
 *
 * @author Graeme Geldenhuys
 *
 */
public class TITouchedByVisitorList extends TIBaseObject {

	private TIVisitorManager visitorManager;
	private Vector<TITouchedByVisitor> objectList;

	public TIVisitorManager getVisitorManager() {
		return visitorManager;
	}

	// the constructor
	public TITouchedByVisitorList(TIVisitorManager visitorManager) {
		this.visitorManager = visitorManager;
		// System.out.println("TITouchedByVisitorList constructor");
		objectList = new Vector<>();
	}

	public int getCount() {
		return objectList.size();
	}

	public TITouchedByVisitor getItem(int index) {
		return objectList.elementAt(index);
	}

}
