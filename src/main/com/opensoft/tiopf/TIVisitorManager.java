package com.opensoft.tiopf;

import java.util.Iterator;
import java.util.Vector;

/**
 * Groups visitors together so they can be passed over a graph of objects together.
 *
 * @author Graeme Geldenhuys
 * @since tiOPF 0.1
 */
public class TIVisitorManager {

	protected Vector<TIVisitorMappingGroup> visitorMappingList;

	public TIVisitorManager() {
		this.visitorMappingList = new Vector<>();
	}

	public void registerVisitor(String groupName, Class<? extends TIVisitor> visitorClass) {
		if ((groupName == null) || groupName.isEmpty())
			throw new IllegalArgumentException("groupName is empty or not assigned.");
		if (visitorClass == null)
			throw new IllegalArgumentException("visitorClass is not assigned.");

		TIVisitorMappingGroup lVisitorMappingGroup = findVisitorMappingGroup(groupName);
		if (lVisitorMappingGroup == null) {
			TIVisitor v;
			try {
				v = visitorClass.newInstance();
				lVisitorMappingGroup = new TIVisitorMappingGroup(groupName, v.visitorControllerClass());
				visitorMappingList.add(lVisitorMappingGroup);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		lVisitorMappingGroup.add(visitorClass);
	}

	public void unregisterVisitor(String groupName) {
		if ((groupName == null) || groupName.isEmpty())
			throw new IllegalArgumentException("groupName is empty or not assigned.");
		TIVisitorMappingGroup visitorMapping = findVisitorMappingGroup(groupName);
		if (visitorMapping == null) {
			throw new RuntimeException("Request to unregister groupName that's not registered <" + groupName + ">");
		}
		visitorMappingList.remove(visitorMapping);
	}

	protected TIVisitorMappingGroup findVisitorMappingGroup(String groupName) {
		if ((groupName == null) || groupName.isEmpty())
			throw new IllegalArgumentException("groupName is empty or not assigned.");
		Iterator<TIVisitorMappingGroup> itr = visitorMappingList.iterator();
		while (itr.hasNext()) {
			TIVisitorMappingGroup lData = itr.next();
			if (lData.getGroupName().equals(groupName.toUpperCase())) {
				return lData;
			}
		}
		return null;
	}

	protected void processVisitors(String groupName, TIVisited visited, TIVisitorControllerConfig visitorControllerConfig) {
		// TODO Complete implementation using VisitorControllerConfig, VisitorMappingGroup etc
		TIVisitorMappingGroup visitorMappingGroup = findVisitorMappingGroup(groupName);
		if (visitorMappingGroup == null)
			throw new RuntimeException("Invalid visitor group <" + groupName + ">");
		TIVisitorController visitorController = visitorMappingGroup.instantiateVisitorControllerClass(this, visitorControllerConfig);

		TIVisitor lVisitor = null;
		Iterator<TIVisitorMappingGroup> iterator = visitorMappingList.iterator();
		if (iterator.hasNext()) {
			TIVisitorMappingGroup lMapping = iterator.next();
			if (lMapping.getGroupName().equals(groupName.toUpperCase())) {
				try {
					lVisitor = lMapping.getVisitorControllerClass().newInstance();
					lVisitor.execute(visited);
					return;
				} catch (IllegalAccessException | InstantiationException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void execute(String groupName, TIVisited visited) {
		if ((groupName == null) || groupName.isEmpty())
			throw new IllegalArgumentException("groupName is empty or not assigned.");
		if (visited == null)
			throw new IllegalArgumentException("visited parameter is not assigned.");

		TIVisitorControllerConfig visitorControllerConfig = new TIVisitorControllerConfig(this);
		processVisitors(groupName, visited, visitorControllerConfig);
	}
}
