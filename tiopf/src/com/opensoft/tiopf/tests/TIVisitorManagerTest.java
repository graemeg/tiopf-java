package com.opensoft.tiopf.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.opensoft.tiopf.TIVisMapping;
import com.opensoft.tiopf.TIVisitor;
import com.opensoft.tiopf.TIVisitorManager;

public class TIVisitorManagerTest {

	private class TestVisitorManagerRegisterVisitor extends TIVisitor {
	}

	private class TestVisitorManager extends TIVisitorManager {

		@Override
		public TIVisMapping findVisitorMapping(String groupName) {
			return super.findVisitorMapping(groupName);
		}

		public Vector<TIVisMapping> getVisitorMappingList() {
			return visitorMappingList;
		}
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegisterVisitor() {
		TestVisitorManager vm = new TestVisitorManager();
		assertEquals(0, vm.getVisitorMappingList().size());
		vm.registerVisitor("test", TestVisitorManagerRegisterVisitor.class);
		assertEquals(1, vm.getVisitorMappingList().size());
		vm.unregisterVisitor("test");
		assertEquals(0, vm.getVisitorMappingList().size());
	}

	@Test
	public void testFindVisitorMapping() {
		TestVisitorManager vm = new TestVisitorManager();
		vm.registerVisitor("test", TIVisitor.class);
		vm.registerVisitor("test1", TIVisitor.class);
		vm.registerVisitor("test2", TIVisitor.class);
		assertNotNull(vm.findVisitorMapping("test"));
		assertNotNull(vm.findVisitorMapping("test1"));
		assertNotNull(vm.findVisitorMapping("test2"));
		assertNull(vm.findVisitorMapping("TEST2")); // to show that case sensitive matching is used
		assertNull(vm.findVisitorMapping("Graeme"));
	}

	// @Test
	public void testUnregisterVisitor() {
		fail("Not yet implemented");
	}

	// @Test
	public void testFindVisitorMappingGroup() {
		fail("Not yet implemented");
	}

}
