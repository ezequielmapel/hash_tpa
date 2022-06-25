package hashdict.applications;

public class HashElement<T extends Object> {
	private String key;
	private T value;
	
	public HashElement(String key, T value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public T getValue() {
		return this.value;
	}
	
	public void setValue (T value) {
		this.value = value;
	}
	
}
