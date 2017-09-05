package com.opensoft.tiopf;

public class TIObject extends TIVisited {

	private TIObject Owner;

	public TIObject() {
		Owner = null;
	}

	public TIObject getOwner() {
		return Owner;
	}

	public void setOwner(TIObject Owner) {
		this.Owner = Owner;
	}

}
