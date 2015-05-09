/**
 * 用来转换数据格式用
 */

/**
 * @author SCM 2012-8-8
 */
public class CommonObject implements Comparable<CommonObject>{

	private String id;
	private String parentid;
	private String name;// 数据项名称
	private String label;// 数据项中文标签
	private String value;// 数据项值
	private String order;// 排列顺序
	private String attribute;// 数据项属性
	private String desc;//描述

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public int compareTo(CommonObject o) {
		if(this.parentid.equals(o.getParentid())){
			return this.order.compareTo(o.getOrder());
		}else{
			throw new RuntimeException("同一层级下才可比较");
		}
	}
}
