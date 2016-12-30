package com.jxutcm.controllers.drugsearch;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.jxutcm.model.Drug;



import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

public class DrugDao {
	public static List<Drug> getAllNewBooks() throws Exception{
		List<Drug> list = new ArrayList<Drug>();
		URL url = new URL("http://book.douban.com/latest");
		URLConnection conn = url.openConnection();
		Source source = new Source(conn);
		List<Element> liList = source.getAllElements("li");
		for(Element element:liList){
			 List<Element>  childrenList = element.getChildElements();
			 if(childrenList.size()==2&&"div".equals(childrenList.get(0).getName())&&"a".equals(childrenList.get(1).getName())){
				 Drug newBook = new Drug();
				 String name = childrenList.get(0).getAllElements().get(0).getTextExtractor().toString();
				 String message = childrenList.get(0).getAllElements().get(1).getTextExtractor().toString();
				 String synopsis = childrenList.get(0).getAllElements().get(2).getTextExtractor().toString();
				 String path = childrenList.get(1).getAllElements().get(1).getAttributeValue("src");
//				 newBook.setDrugName(name);
//				 newBook.setDrugProducingArea(message);
//				 newBook.setDrugFunctions(synopsis);
//				 newBook.setDrugPicturePath(path);
				 list.add(newBook);
			 }
		}
		return list;
	}
}
