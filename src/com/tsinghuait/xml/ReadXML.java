package com.tsinghuait.xml;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * XML ��ȡ��ʽ��
 * 1. dom
 * 2. dom4j
 * 3. jdom
 * 4. sax
 * @author ssr
 *
 */
public class ReadXML {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String path = ReadXML.class.getClassLoader().getResource("book.xml").getPath();
		System.out.println(path);

		// ����SAXReader����������xml
		SAXReader sax = new SAXReader();

		try {
			Document doc = sax.read(path);
			Element root=doc.getRootElement();
			System.out.println(root.getName());
			
			// root�µ����н��
			List<Element> list=root.elements();
			
			// ����list
			for(Element el:list) {
				getElement(el);
			}
				
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void getElement(Element e) {
		List<Element> list=e.elements();
		if(list.size()>=1) {
			for(int i=0;i<list.size();i++){
				getElement(list.get(i));
			}
		}else {
			System.out.println("�������֣�"+e.getName()+" �ڵ��ֵ��"+e.getTextTrim());
			System.out.println("�������Ը�����"+e.attributeCount());
			System.out.println("�ڵ���������ֵ��---------------------------");
			List<Attribute> attrs=e.attributes();
			for(Attribute el: attrs){
				System.out.println(el.getName()+"===="+el.getText());
			}
			System.out.println(e.getNodeTypeName());
		}
	}
}
