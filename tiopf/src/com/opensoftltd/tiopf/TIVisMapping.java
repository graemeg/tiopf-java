package com.opensoftltd.tiopf;

public class TIVisMapping {

	private String command;
	private Class<? extends TIVisitor> visitorClass;

	public TIVisMapping() {
	}

	public Class<? extends TIVisitor> getVisitorClass() {
		return visitorClass;
	}

	public void setVisitorClass(Class<? extends TIVisitor> visitorClass) {
		this.visitorClass = visitorClass;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
