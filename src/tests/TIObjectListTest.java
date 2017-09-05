import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.opensoft.tiopf.TIObject;
import com.opensoft.tiopf.TIObjectList;

public class TIObjectListTest {

	private TIObjectList ol;

	@Before
	public void setUp() throws Exception {
		ol = new TIObjectList();
	}

	@After
	public void tearDown() throws Exception {
		ol = null;
	}

	@Test
	public void testGetItemOwner() {
		TIObjectList lList;
		TIObject lItem;
		TIObject lItemOwner;

		// Default behaviour
		try {
			lList = new TIObjectList();
			lItem = new TIObject();
			lItemOwner = new TIObject();
			assertNull("failed on 1", lItem.getOwner());
			assertEquals("failed on 2", lList.getAutoSetItemOwner(), true);
			assertEquals("failed on 3", lList, lList.getItemOwner());
			lList.add(lItem);
			assertEquals("failed on 4", lList, lItem.getOwner());
		} finally {
			lList = null;
			lItemOwner = null;
		}

		// TIObjectList.ItemOwner is set
		try {
			lList = new TIObjectList();
			lItem = new TIObject();
			lItemOwner = new TIObject();
			lList.setItemOwner(lItemOwner);
			assertNull("failed on 5", lItem.getOwner());
			assertEquals("failed on 6", lList.getAutoSetItemOwner(), true);
			assertEquals("failed on 7", lItemOwner, lList.getItemOwner());
			lList.add(lItem);
			assertEquals("failed on 8", lItemOwner, lItem.getOwner());
		} finally {
			lList = null;
			lItemOwner = null;
		}

		// TIObjectList.AutoSetItemOwner = false
		try {
			lList = new TIObjectList();
			lItem = new TIObject();
			lList.setAutoSetItemOwner(false);
			assertNull("failed on 9", lItem.getOwner());
			assertEquals("failed on 10", lList.getAutoSetItemOwner(), false);
			assertEquals("failed on 11", lList, lList.getItemOwner());
			lList.add(lItem);
			assertNull("failed on 12", lItem.getOwner());
		} finally {
			lList = null;
			lItemOwner = null;
		}
	}

	@Test
	public void testAdd() {
		assertEquals("failed on 1", 0, ol.getCount());
		TIObject lData = new TIObject();
		ol.add(lData);
		assertEquals("failed on 2", 1, ol.getCount());
	}

	@Test
	public void testGetCount() {
		assertEquals("failed on 1", 0, ol.getCount());
		TIObject lData = new TIObject();
		ol.add(lData);
		assertEquals("failed on 2", 1, ol.getCount());
	}

}
