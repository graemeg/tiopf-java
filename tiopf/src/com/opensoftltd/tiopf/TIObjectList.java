package com.opensoftltd.tiopf;

import java.util.Vector;

public class TIObjectList extends TIObject {

	private Vector<TIObject> fList;

	public TIObjectList() {
		fList = new Vector<TIObject>();
	}

	public void add(TIObject aData) {
		fList.add(aData);
	}

	@Override
	public void Iterate(TIVisitor aVisitor) {
		super.Iterate(aVisitor);
		for (int i = 0; i < fList.size(); i++) {
			TIObject item = fList.get(i);
			item.Iterate(aVisitor);
		}
	}

	public int Count() {
		return fList.size();
	}

	public int Size() {
		return fList.size();
	}

}
