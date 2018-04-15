package com.opensoft.tiopf;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
			try {
				TIVisitor v = visitorClass.newInstance();
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
		// TODO Complete implementation using VisitorControllerConfig,
		// VisitorMappingGroup etc
		TIVisitorMappingGroup visitorMappingGroup = findVisitorMappingGroup(groupName);
		if (visitorMappingGroup == null)
			throw new RuntimeException("Invalid visitor group <" + groupName + ">");
		// TIVisitorController visitorController =
		// visitorMappingGroup.instantiateVisitorControllerClass(this,
		// visitorControllerConfig);

		Vector<TIVisitor> lVisitorList = new Vector<>();

		try {
			Class cls[] = new Class[] { TIVisitorManager.class, TIVisitorControllerConfig.class };
			Constructor ctor = visitorMappingGroup.getVisitorControllerClass().getConstructor(cls);
			TIVisitorController lVisitorController = null;
			lVisitorController = (TIVisitorController) ctor.newInstance(this, visitorControllerConfig);

			// ******************** graeme: 2018-04-11 Start here! *************************
			// TODO: implement the assignVisitorInstances()
			assignVisitorInstances(visitorMappingGroup, lVisitorList);

			lVisitorController.beforeExecuteVisitorGroup();
			try {
				executeVisitors(lVisitorController, lVisitorList, visited);
				lVisitorController.afterExecuteVisitorGroup(lVisitorController.getTouchedByVisitorList());
			} catch (Exception e) {
				lVisitorController.afterExecuteVisitorGroupError();
			}

			return;
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	private void executeVisitors(TIVisitorController visitorController, Vector<TIVisitor> visitorList, TIVisited visited) {
		// TODO Implement me!
		Iterator<TIVisitor> iterator = visitorList.iterator();
		if (iterator.hasNext()) {
			TIVisitor v = iterator.next();
			visitorController.beforeExecuteVisitor(v);
			try {
				if (visited != null) {
					visited.iterateAssignTouched(v, visitorController.getTouchedByVisitorList());
				} else {
					v.execute(null);
				}
				// visited.Iterate(v);
			} finally {
				visitorController.afterExecuteVisitor(v);
			}

		}

	}

	private void assignVisitorInstances(TIVisitorMappingGroup visitorMappingGroup, Vector<TIVisitor> lVisitorList) {
		// TODO Implement me!

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
