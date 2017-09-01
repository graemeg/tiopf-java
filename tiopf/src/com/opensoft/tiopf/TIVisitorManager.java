package com.opensoft.tiopf;

import java.util.Iterator;
import java.util.Vector;

public class TIVisitorManager {

	protected Vector<TIVisMapping> visitorMappingList;

	public TIVisitorManager() {
		this.visitorMappingList = new Vector<>();
	}

	public void registerVisitor(String groupName, Class<? extends TIVisitor> visitorClass) {
		if (groupName.isEmpty())
			throw new IllegalArgumentException("groupName can't be blank.");
		if (visitorClass == null)
			throw new IllegalArgumentException("visitorClass not assigned.");
		TIVisMapping lData = new TIVisMapping();
		lData.setCommand(groupName);
		lData.setVisitorClass(visitorClass);
		visitorMappingList.add(lData);
	}

	public void execute(String groupName, TIVisited visited) {
		TIVisitor lVisitor = null;
		Iterator<TIVisMapping> iterator = visitorMappingList.iterator();
		if (iterator.hasNext()) {
			TIVisMapping lMapping = iterator.next();
			if (lMapping.getCommand().equalsIgnoreCase(groupName)) {
				try {
					lVisitor = lMapping.getVisitorClass().newInstance();
					lVisitor.execute(visited);
					return;
				} catch (IllegalAccessException | InstantiationException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
