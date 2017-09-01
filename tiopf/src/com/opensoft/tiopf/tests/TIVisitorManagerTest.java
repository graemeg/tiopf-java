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
		assertEquals("Failed on 1", 0, vm.getVisitorMappingList().size());
		vm.registerVisitor("test", TestVisitorManagerRegisterVisitor.class);
		assertEquals("Failed on 2", 1, vm.getVisitorMappingList().size());
		vm.unregisterVisitor("test");
		assertEquals("Failed on 3", 0, vm.getVisitorMappingList().size());

		vm.registerVisitor("test", TestVisitorManagerRegisterVisitor.class);
		assertEquals("Failed on 4", 1, vm.getVisitorMappingList().size());
		vm.unregisterVisitor("TEST");
		assertEquals("Failed on 5", 0, vm.getVisitorMappingList().size());
	}

	@Test
	public void testRegisterVisitor_with_errors() {
		TestVisitorManager vm = new TestVisitorManager();
		try {
			// groupName cannot be empty
			vm.registerVisitor("", TestVisitorManagerRegisterVisitor.class);
			fail("Failed on 1");
		} catch (IllegalArgumentException e) {
			// do nothing as it was a success
		}
		assertEquals("Failed on 2", 0, vm.getVisitorMappingList().size());
		try {
			// visitorClass cannot be null
			vm.registerVisitor("test", null);
			fail("Failed on 3");
		} catch (IllegalArgumentException e) {
			// do nothing as it was a success
		}
		assertEquals("Failed on 4", 0, vm.getVisitorMappingList().size());
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
		assertNotNull(vm.findVisitorMapping("TEST2")); // to show that case insensitive matching is used
		assertNull(vm.findVisitorMapping("Graeme"));
	}

	@Test
	public void testUnregisterVisitor() {
		TestVisitorManager vm = new TestVisitorManager();
		vm.registerVisitor("test1", TIVisitor.class);
		vm.registerVisitor("test2", TIVisitor.class);
		assertEquals("Failed on 1", 2, vm.getVisitorMappingList().size());
		vm.unregisterVisitor("test1");
		assertEquals("Failed on 2", 1, vm.getVisitorMappingList().size());
		vm.unregisterVisitor("TEST2"); // to show that case insensitive match is used
		assertEquals("Failed on 3", 0, vm.getVisitorMappingList().size());
	}

	@Test
	public void testUnregisterVisitor_with_errors() {
		TestVisitorManager vm = new TestVisitorManager();
		vm.registerVisitor("test1", TIVisitor.class);
		vm.registerVisitor("test2", TIVisitor.class);
		assertEquals(2, vm.getVisitorMappingList().size());
		try {
			vm.unregisterVisitor("Graeme");
			fail("Failed on 1");
		} catch (RuntimeException e) {
			// do nothing - it worked as it should have
		}
		assertEquals("Failed on 2", 2, vm.getVisitorMappingList().size());
	}

	// @Test
	public void testFindVisitorMappingGroup() {
		fail("Not yet implemented");
	}

}
