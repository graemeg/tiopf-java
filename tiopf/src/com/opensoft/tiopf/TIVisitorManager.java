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

	public void unregisterVisitor(String groupName) {
		if (groupName.isEmpty())
			throw new IllegalArgumentException("groupName is empty or not assigned.");
		TIVisMapping visitorMapping = findVisitorMapping(groupName);
		if (visitorMapping == null) {
			throw new RuntimeException("Request to Unregister visitor group that's not registered <" + groupName + ">");
		}
		visitorMappingList.remove(visitorMapping);
	}

	protected TIVisMapping findVisitorMapping(String groupName) {
		Iterator<TIVisMapping> itr = visitorMappingList.iterator();
		while (itr.hasNext()) {
			TIVisMapping lData = itr.next();
			if (lData.getCommand().equals(groupName)) {
				return lData;
			}
		}
		return null;
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
