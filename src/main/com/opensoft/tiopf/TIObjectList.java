package com.opensoft.tiopf;

import java.util.Vector;

public class TIObjectList extends TIObject {

	private Vector<TIObject> fList;
	private TIObject itemOwner = null;
	private boolean AutoSetItemOwner = true;

	public TIObject getItemOwner() {
		return itemOwner;
	}

	public void setItemOwner(TIObject itemOwner) {
		this.itemOwner = itemOwner;
	}

	public TIObjectList() {
		fList = new Vector<>();
		itemOwner = this;
	}

	public void add(TIObject aData) {
		if (AutoSetItemOwner)
			aData.setOwner(itemOwner);
		fList.add(aData);
		// TODO: Call notifyObservers() here
	}

	@Override
	public void Iterate(TIVisitor aVisitor) {
		super.Iterate(aVisitor);
		for (int i = 0; i < fList.size(); i++) {
			TIObject item = fList.get(i);
			item.Iterate(aVisitor);
		}
	}

	public int getCount() {
		return fList.size();
	}

	public int Size() {
		return fList.size();
	}

	public boolean getAutoSetItemOwner() {
		return AutoSetItemOwner;
	}

	public void setAutoSetItemOwner(boolean Value) {
		AutoSetItemOwner = Value;
	}

}
