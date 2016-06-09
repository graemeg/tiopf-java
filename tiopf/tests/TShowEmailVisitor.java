
public class TShowEmailVisitor extends TVisitor {

	public TShowEmailVisitor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Object obj) {
		System.out.println("ShowEmailVisitor is executing against " + obj.getClass().getName());

	}

}
