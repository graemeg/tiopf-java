
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.opensoft.tiopf.TIVisitor;
import com.opensoft.tiopf.TIVisitorManager;
import com.opensoft.tiopf.TIVisitorMappingGroup;

public class TIVisitorManagerTest {

	private class TestVisitorManagerRegisterVisitor extends TIVisitor {
	}

	private class TestVisitorManager extends TIVisitorManager {

		@Override
		public TIVisitorMappingGroup findVisitorMappingGroup(String groupName) {
			return super.findVisitorMappingGroup(groupName);
		}

		public Vector<TIVisitorMappingGroup> getVisitorMappingList() {
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
			fail("Failed on 1 - exception not raised");
		} catch (IllegalArgumentException e) {
			// do nothing as it was a success
		}
		assertEquals("Failed on 2", 0, vm.getVisitorMappingList().size());
		try {
			// visitorClass cannot be null
			vm.registerVisitor("test", null);
			fail("Failed on 3 - exception not raised");
		} catch (IllegalArgumentException e) {
			// do nothing as it was a success
		}
		assertEquals("Failed on 4", 0, vm.getVisitorMappingList().size());
	}

	@Test
	public void testRegisterVisitor_with_duplicates() {
		TestVisitorManager vm = new TestVisitorManager();
		assertEquals("Failed on 1", 0, vm.getVisitorMappingList().size());
		vm.registerVisitor("test", TestVisitorManagerRegisterVisitor.class);
		assertEquals("Failed on 2", 1, vm.getVisitorMappingList().size());

		// should fail as it is a duplicate
		vm.registerVisitor("test", TestVisitorManagerRegisterVisitor.class);
		assertEquals("Failed on 3", 1, vm.getVisitorMappingList().size());
	}

	@Test
	public void testFindVisitorMapping() {
		TestVisitorManager vm = new TestVisitorManager();
		vm.registerVisitor("test", TIVisitor.class);
		vm.registerVisitor("test1", TIVisitor.class);
		vm.registerVisitor("test2", TIVisitor.class);
		assertNotNull(vm.findVisitorMappingGroup("test"));
		assertNotNull(vm.findVisitorMappingGroup("test1"));
		assertNotNull(vm.findVisitorMappingGroup("test2"));
		assertNotNull(vm.findVisitorMappingGroup("TEST2")); // to show that case insensitive matching is used
		assertNull(vm.findVisitorMappingGroup("Graeme"));
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
			fail("Failed on 1 - exception not raised");
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
