package batchJob.test;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ApplicatinTest {
	
	public static void main(String[] arg ) {
		
		ApplicatinTest.propertiesTest();
	}
	
	public static void propertiesTest() {
		
		try {
			SAXReader reader = new SAXReader(); 
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.xml");
			Document doc = reader.read(in);
			Element rootElt = doc.getRootElement(); 
			
			Element doElement = rootElt.element("application"); 
			
			System.out.println(doElement.attributeValue("id"));
			Element forwardElement =doElement.element("property");
			
			System.out.println(forwardElement.attributeValue("name"));
			System.out.println(forwardElement.attributeValue("value"));
			
			List<Element> aaqqqaElement = doElement.elements("property");
			Element aaaString = aaqqqaElement.get(4);
			System.out.println(aaaString.attributeValue("id"));
			System.out.println(aaaString.attributeValue("name"));
			System.out.println(aaaString.attributeValue("value"));
			System.out.println("9999"+aaqqqaElement.indexOf("CPG_DEQ_SYSCODE"));
			
			
			Element aaaElement = doElement.element("settings");
			Element bbbElement = aaaElement.element("set");
			
			System.out.println(bbbElement.attributeValue("name"));
			System.out.println(bbbElement.attributeValue("value"));
			
			//获取forward节点中的属性和文本值
			
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			System.out.println("do节点："+doElement.getName());
			System.out.println("do节点的属性path和type分别为："+doElement.attributeValue("path")+"、"+doElement.attributeValue("type"));
			System.out.println("forward节点："+forwardElement.getName());
			System.out.println("forward节点属性name为："+forwardElement.attributeValue("name"));
			System.out.println("forward节点的文本值为："+forwardElement.getText());
			
			//获取forward节点中的属性和文本值
			String name=forwardElement.attributeValue("name");
			String value=forwardElement.getText();
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			System.out.println("do节点："+doElement.getName());
			System.out.println("do节点的属性path和type分别为："+doElement.attributeValue("path")+"、"+doElement.attributeValue("type"));
			System.out.println("forward节点："+forwardElement.getName());
			System.out.println("forward节点属性name为："+forwardElement.attributeValue("name"));
			System.out.println("forward节点的文本值为："+forwardElement.getText());
			
//			org.dom4j.tree.DefaultDocument@420f9c40 [Document: name null]
//
//			org.dom4j.tree.DefaultElement@217431b9 [Element: <do-config attributes: []/>]
//
//			org.dom4j.tree.DefaultElement@426295eb [Element: <do attributes: [org.dom4j.tree.DefaultAttribute@56609959 [Attribute: name path value "User/adduser"], org.dom4j.tree.DefaultAttribute@5ff3ce5c [Attribute: name type value "UserPackage.UserServlet"]]/>]
//
//			User/adduser
//
//			UserPackage.UserServlet
//
//
//			根节点：do-config
//			do节点：do
//			do节点的属性path和type分别为：User/adduser、UserPackage.UserServlet
//			forward节点：forward
//			forward节点属性name为：Success
//			forward节点的文本值为：AddSuccess.jsp
//
//			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
//			System.out.println("do节点："+doElement.getName());
//			System.out.println("do节点的属性path和type分别为："+doElement.attributeValue("path")+"、"+doElement.attributeValue("type"));
//			System.out.println("forward节点："+forwardElement.getName());
//			System.out.println("forward节点属性name为："+forwardElement.attributeValue("name"));
//			System.out.println("forward节点的文本值为："+forwardElement.getText());
//
//			<do-config>
//				<do path="User/adduser" type="UserPackage.UserServlet">
//					<forward name="Success">AddSuccess.jsp</forward>
//				</do>
//			</do-config>

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}

}
