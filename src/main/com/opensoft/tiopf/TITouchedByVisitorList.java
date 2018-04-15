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

	/**
	 * the constructor - in Delphi we have a parameter that says if the objectList owns the objects or not. Is there
	 * ever such a case? I haven't see it, so decided to ignore that functionality. Our objectList never owns the
	 * objects it holds.
	 */
	public TITouchedByVisitorList() {
		objectList = new Vector<>();
	}

	public int getCount() {
		return objectList.size();
	}

	public TITouchedByVisitor getItem(int index) {
		return objectList.elementAt(index);
	}

	public void add(TITouchedByVisitor item) {
		objectList.add(item);
	}

}
