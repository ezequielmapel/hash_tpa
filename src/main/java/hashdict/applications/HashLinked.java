package hashdict.applications;

import java.util.LinkedList;
import java.util.List;

public class HashLinked <T extends Object> {
	private List<HashElement<?>> row = new LinkedList<>();
	
	public HashLinked(HashElement<T> element) {
		this.row.add(element);
	}

	public List<HashElement<?>> getRow() {
		return row;
	}
	public void setRow(List<HashElement<?>> row) {
		this.row = row;
	}
	
	
	
}
