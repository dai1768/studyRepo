
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 树节点对象
 * @author daigg
 * @date 2015-01-12
 * @param <T>
 */
@XmlRootElement(name="treeNode")
@XmlAccessorType(XmlAccessType.FIELD)  
public class TreeNode<T  extends Comparable<? super T>> implements Comparable<TreeNode<T>>{
	@XmlAnyElement
	@XmlElementWrapper
	private  Set<TreeNode<T>> children ;
	@XmlTransient
	private  TreeNode<T> parent;
	@XmlElement(type=Item.class)
	private  T data;
	
	public TreeNode() {
		super();
	}
	
	public TreeNode(Set<TreeNode<T>> children, TreeNode<T> parent, T data) {
		super();
		this.children = children;
		this.parent = parent;
		this.data = data;
	}


	public Set<TreeNode<T>> getChildren() {
		return children;
	}

	public void setChildren(Set<TreeNode<T>> children) {
		this.children = children;
	}

	public TreeNode<T> getParent() {
		return parent;
	}

	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static void main(String[] args) {
	}

	
	@Override
	public int compareTo(TreeNode<T> toCompare) {
		int result = 0;
		if(this.parent == toCompare.parent){
			result = data.compareTo(toCompare.getData());
		}else{
			new RuntimeException("不同层次节点不可比较！");
		}
		return result;
	}
	
	
}



