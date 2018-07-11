package com.tsinghuait.xml;

import java.io.File;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ModifyXML {

	public static void main(String[] args) throws Exception {
		String path = ModifyXML.class.getClassLoader().getResource("book.xml").getPath();
		SAXReader sax = new SAXReader();

		try {
			Document doc = sax.read(new File(path));
			
			Element root = doc.getRootElement();
			
			System.out.println(root);
			
			// 添加节点
			Element newBook=root.addElement("cat");
			newBook.addText("TomCat");
			//添加没有内容的节点
			root.addElement("Dog");
			
			//获取属性、删除属性
			Attribute address = root.attribute("address");
			root.remove(address);
			
			Attribute category = root.attribute("category");
			root.remove(category);
			
			ListNodes.getElement(root);
			
			ListNodes.writeDocumentToFile(doc, "src/newUser.xml");
			
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
