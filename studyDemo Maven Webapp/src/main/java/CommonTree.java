
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
@XmlRootElement(name="tree")
@XmlAccessorType(XmlAccessType.FIELD)  
public class CommonTree<T  extends Comparable<? super T>> {
	@XmlElement
	private TreeNode<T> root;
	
	public CommonTree() {
		super();
	}

	public CommonTree( Set<TreeNode<T>> children, TreeNode<T> parent, T data){
		root = new TreeNode<T>(children,parent,data);
	}

	public TreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(TreeNode<T> root) {
		this.root = root;
	}
	
	public String toXML(){
		Document doc = DocumentHelper.createDocument(); 
		doc.setXMLEncoding("UTF-8");
		Element tree = doc.addElement("tree");
		tree.addElement("data");
		
		return "";
	}
	public void xml2Tree(String xml){
		
	}
	public static void main(String[] args) throws JAXBException {
		System.out.println("11");
		 HashSet<TreeNode<Item>> children = new HashSet<TreeNode<Item>>();
		 Set<TreeNode<Item>> children01 =new HashSet<TreeNode<Item>>();
		 Item it0 = new Item("a0","b0","c0");
		 Item it1 = new Item("a1","b1","c1");
		 Item it2 = new Item("a2","b2","c2");
		 TreeNode<Item> node0 = new TreeNode<Item>(children01,null,it0);
		 TreeNode<Item> node1 = new TreeNode<Item>(null,null,it1);
		 TreeNode<Item> node2 = new TreeNode<Item>(null,null,it2);
		children.add(node0);
		children.add(node1);
		children.add(node2);
		TreeNode<Item> node00 = new TreeNode<Item>(null,node0,it0);
		TreeNode<Item> node01 = new TreeNode<Item>(null,node0,it1);
		children01.add(node00);
		children01.add(node01);;
		
		CommonTree<Item> tree = new CommonTree<Item>(children,null,null);
		JAXBContext context = JAXBContext.newInstance(tree.getClass());
		context.createMarshaller().marshal(tree,System.out);
	}
}
class Test{
	
}