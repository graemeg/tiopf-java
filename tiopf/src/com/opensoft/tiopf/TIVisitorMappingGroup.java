package com.opensoft.tiopf;

public class TIVisitorMappingGroup {

	private String GroupName;
	private Class<? extends TIVisitor> visitorClass;

	public TIVisitorMappingGroup() {
	}

	public Class<? extends TIVisitor> getVisitorClass() {
		return visitorClass;
	}

	public void setVisitorClass(Class<? extends TIVisitor> visitorClass) {
		this.visitorClass = visitorClass;
	}

	public String getCommand() {
		return GroupName;
	}

	public void setCommand(String command) {
		this.GroupName = command;
	}

}
