import com.opensoftltd.tiopf.TIObject;
import com.opensoftltd.tiopf.TIObjectList;
import com.opensoftltd.tiopf.TIVisited;
import com.opensoftltd.tiopf.TIVisitor;

public class MainTest {

	TIObjectList list;

	private class Person extends TIObject {

		private String firstName;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		@Override
		public String toString() {
			return getFirstName();
		}

	}

	private class VisPersonDebugToString extends TIVisitor {

		@Override
		public void execute(TIVisited aVisited) {
			super.execute(aVisited);
			if (acceptVisitor() == false) {
				return;
			}
			System.out.println(aVisited.toString());
		}

		@Override
		protected boolean acceptVisitor() {
			// for more Type and Inheritance examples:
			// http://www.mytechnotes.biz/2012/07/java-instanceof-isassignablefrom-or.html
			return getVisited() instanceof Person;
			// return getVisited().getClass().isInstance(Person.class);
			// return Person.class.isInstance(getVisited());
		}

	}

	public MainTest() {
		list = new TIObjectList();

		// VisPersonDebugToString.class;
	}

	public void Execute() {
		Person p1 = new Person();
		p1.setFirstName("Graeme");
		list.add(p1);

		Person p2 = new Person();
		p2.setFirstName("Angela");
		list.add(p2);

		VisPersonDebugToString lVis = new VisPersonDebugToString();
		list.Iterate(lVis);

		System.out.print("List count = " + list.Count());
	}

	public static void main(String[] args) {
		MainTest t = new MainTest();
		t.Execute();
	}

}
