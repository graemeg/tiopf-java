package com.opensoftltd.tiopf;

import java.util.Iterator;
import java.util.Vector;

public class TIVisitorManager {

	private Vector<TIVisMapping> fList;

	public TIVisitorManager() {
		fList = new Vector<TIVisMapping>();
	}

	public void registerVisitor(String groupName, Class<? extends TIVisitor> visitorClass) {
		TIVisMapping lData = new TIVisMapping();
		lData.setCommand(groupName);
		lData.setVisitorClass(visitorClass);
		fList.add(lData);
	}

	public void execute(String groupName, TIVisited visited) {
		TIVisitor lVisitor = null;
		Iterator<TIVisMapping> iterator = fList.iterator();
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
