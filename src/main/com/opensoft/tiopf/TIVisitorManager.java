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
			throw new IllegalArgumentException("visitorClass parameter is not assigned.");

		TIVisitorMappingGroup lVisitorMappingGroup;
		lVisitorMappingGroup = findVisitorMappingGroup(groupName);
		if (lVisitorMappingGroup == null) {
			lVisitorMappingGroup = new TIVisitorMappingGroup();
			lVisitorMappingGroup.setCommand(groupName.toUpperCase());
			lVisitorMappingGroup.setVisitorClass(visitorClass);
			visitorMappingList.add(lVisitorMappingGroup);
		}
		// lData.add(visitorClass);
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
			if (lData.getCommand().equals(groupName.toUpperCase())) {
				return lData;
			}
		}
		return null;
	}

	public void execute(String groupName, TIVisited visited) {
		if ((groupName == null) || groupName.isEmpty())
			throw new IllegalArgumentException("groupName is empty or not assigned.");
		if (visited == null)
			throw new IllegalArgumentException("visited parameter is not assigned.");
		TIVisitor lVisitor = null;
		Iterator<TIVisitorMappingGroup> iterator = visitorMappingList.iterator();
		if (iterator.hasNext()) {
			TIVisitorMappingGroup lMapping = iterator.next();
			if (lMapping.getCommand().equals(groupName.toUpperCase())) {
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
