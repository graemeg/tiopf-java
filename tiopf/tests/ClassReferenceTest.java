
public class ClassReferenceTest {

	public void run() {
		System.out.println("Calling executeVisitor() from ClassReferenceTest...");
		executeVisitor(this, TShowNameVisitor.class);
		executeVisitor(this, TShowEmailVisitor.class);
	}

	public void executeVisitor(ClassReferenceTest pData, Class<? extends TVisitor> pVisClass) {
		TVisitor lVisitor = null;

		try {
			lVisitor = pVisClass.newInstance();
			lVisitor.execute(this);
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		ClassReferenceTest t = new ClassReferenceTest();
		t.run();

	}

}
