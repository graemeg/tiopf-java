package com.opensoft.tiopf;

public class TIVisitorController extends TIBaseObject {

	private TIVisitorControllerConfig config;
	private TITouchedByVisitorList touchedByVisitorList;
	private TIVisitorManager visitorManager;

	public TIVisitorController(TIVisitorManager visitorManager, TIVisitorControllerConfig config) {
		this.visitorManager = visitorManager;
		this.config = config;
		touchedByVisitorList = new TITouchedByVisitorList(this.visitorManager);
	}

	protected TIVisitorControllerConfig getConfig() {
		return config;
	}

	protected TIVisitorManager getVisitorManager() {
		return visitorManager;
	}

	public final TITouchedByVisitorList getTouchedByVisitorList() {
		return touchedByVisitorList;
	}

	public void beforeExecuteVisitorGroup() {
		// can be overridden in descendants
	}

	public void beforeExecuteVisitor(TIVisitor visitor) {
		// can be overridden by descendants
	}

	public void afterExecuteVisitor(TIVisitor visitor) {
		// can be overridden by descendants
	}

	public void afterExecuteVisitorGroup(TITouchedByVisitorList touchedByVisitorList) {
		// can be overridden by descendants
	}

	public void afterExecuteVisitorGroupError() {
		// can be overridden by descendants
	}

}
