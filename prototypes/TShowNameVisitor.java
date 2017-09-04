
public class TShowNameVisitor extends TVisitor {

	public TShowNameVisitor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Object obj) {
		System.out.println("ShowNameVisitor is executing against " + obj.getClass().getName());

	}

}
