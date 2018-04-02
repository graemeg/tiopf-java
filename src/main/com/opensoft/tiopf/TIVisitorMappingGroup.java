package com.opensoft.tiopf;

public class TIVisitorMappingGroup extends TIBaseObject {

	private String groupName;
	private Class<? extends TIVisitorController> visitorClass;

	public TIVisitorMappingGroup(String groupName, Class<? extends TIVisitorController> visitorControllerClass) {
		super();
		this.groupName = groupName.toUpperCase();
		this.visitorClass = visitorControllerClass;
	}

	public Class<? extends TIVisitorController> getVisitorControllerClass() {
		return visitorClass;
	}

	public String getGroupName() {
		return groupName;
	}

	public void add(Class<? extends TIVisitor> visitorClass2) {
		// TODO Auto-generated method stub

	}

}
