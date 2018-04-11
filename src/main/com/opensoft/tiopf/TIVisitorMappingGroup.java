package com.opensoft.tiopf;

import java.util.Iterator;
import java.util.Vector;

public class TIVisitorMappingGroup extends TIBaseObject {

	private String groupName;
	private Class<? extends TIVisitorController> visitorClass;
	private Vector<Class<? extends TIVisitor>> mappings;

	public TIVisitorMappingGroup(String groupName, Class<? extends TIVisitorController> visitorControllerClass) {
		super();
		this.groupName = groupName.toUpperCase();
		this.visitorClass = visitorControllerClass;
		mappings = new Vector<>();
	}

	public Class<? extends TIVisitorController> getVisitorControllerClass() {
		return visitorClass;
	}

	public String getGroupName() {
		return groupName;
	}

	public void add(Class<? extends TIVisitor> visitorClass2) {
		mappings.add(visitorClass2);
	}

	public void assignVisitorInstances(Vector<TIVisitor> objectList) {
		Iterator<Class<? extends TIVisitor>> i = mappings.iterator();
		while (i.hasNext()) {
			try {
				objectList.add(i.next().newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public TIVisitorController instantiateVisitorControllerClass(TIVisitorManager tiVisitorManager,	TIVisitorControllerConfig visitorControllerConfig) {
		try {
			TIVisitorController ins = visitorClass.newInstance();
			ins.setVisitorManager(tiVisitorManager);
			ins.setVisitorControllerConfig(visitorControllerConfig);
			return ins;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

}
