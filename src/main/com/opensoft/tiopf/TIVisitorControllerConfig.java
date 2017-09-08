package com.opensoft.tiopf;

public class TIVisitorControllerConfig extends TIBaseObject {

	private TIVisitorManager visitorManager;

	protected TIVisitorManager getVisitorManager() {
		return visitorManager;
	}

	public TIVisitorControllerConfig(TIVisitorManager visitorManager) {
		this.visitorManager = visitorManager;
	}

}
