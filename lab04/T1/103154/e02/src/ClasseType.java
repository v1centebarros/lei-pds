
public enum ClasseType {
	E("Executiva"), T("Turística");

	private String label;

	private ClasseType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
