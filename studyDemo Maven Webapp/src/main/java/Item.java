import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="data")
@XmlAccessorType(XmlAccessType.FIELD) 
public class Item implements Comparable<Item>{
	@XmlElement
	private String name;
	@XmlElement
	private String value;
	@XmlElement
	private String label;
	
	
	public Item(String name, String value, String label) {
		super();
		this.name = name;
		this.value = value;
		this.label = label;
	}
	
	public Item() {
		super();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public int compareTo(Item o) {
		
		return value.compareTo(o.getValue());
	}
	

}
