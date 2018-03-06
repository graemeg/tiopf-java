package com.opensoft.tiopf;

import java.util.Vector;

/**
 * A list of TITouchedByVisitor
 *
 * @author Graeme Geldenhuys
 *
 */
public class TITouchedByVisitorList extends TIBaseObject {

	private Vector<TITouchedByVisitor> objectList;

	// the constructor
	public TITouchedByVisitorList() {
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
