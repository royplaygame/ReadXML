package com.tsinghuait.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

@SuppressWarnings("unchecked")
public class ListNodes {

	public static void listAllNodes(String fileName) {
		// 获取路径
		String path = ListNodes.class.getClassLoader().getResource(fileName).getPath();
		System.out.println(path);

		// 创建SAXReader对象
		SAXReader sax = new SAXReader();

		// 获取Document对象
		Document doc = null;
		try {
			doc = sax.read(new File(path));
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}

		// 获取元素的根结点
		Element root = doc.getRootElement();
		System.out.println("根结点名称： " + root.getName());
		// 获取根元素下的子结点
		List<Element> list = root.elements();
		for (Element e : list) {
			getElement(e);
		}
	}

	public static void getElement(Element e) {
		System.out.println("结点名称： " + e.getName());
		List<Attribute> attrs = e.attributes();
		for (Attribute a : attrs) {
			System.out.print("属性名称：" + a.getName() + "\t 属性值：" + a.getValue() + " \t");
		}
		System.out.println();
		List<Element> list = e.elements();
		if (list.size() >= 1) {
			for (Element el : list) {
				getElement(el);
			}
		} else {
			System.out.println("子节点名称：" + e.getName() + "\t 子节点值：" + e.getTextTrim() + "\t 类型：" + e.getNodeTypeName());
			List<Attribute> subAttrs = e.attributes();
			for (Attribute a : subAttrs) {
				System.out.println("属性名称：" + a.getName() + "\t 属性值：" + a.getValue() + " \t");
			}
		}
		System.out.println("\n---------------------------------------------------------------");
	}

	public static void writeDocumentToFile(Document doc, String fileName) throws Exception {
		// 输出格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置编码
		format.setEncoding("utf-8");
		// 指定输入文件及格式
		FileOutputStream out = new FileOutputStream(new File(fileName));
		OutputStreamWriter os = new OutputStreamWriter(out, "utf-8");
		XMLWriter writer = new XMLWriter(os, format);

		// 写入文件
		writer.write(doc);
		writer.flush();
		// 释放资源
		writer.close();
		os.close();
		out.close();
	}
}
