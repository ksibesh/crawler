package com.html.crawler.processor;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.html.crawler.constants.SystemConstants;
import com.html.crawler.dto.InputPatternDTO;
import com.html.crawler.utils.CommonUtils;

public class ResourceHtmlProcessor {

	public String processUrl(String url, InputPatternDTO pattern) throws IOException {
		Document doc = Jsoup.connect(url).get();
		if (!pattern.isRoot()) {
			return null;
		}

		JsonArray dataArray = new JsonArray();

		Element element = doc.select(pattern.getCssSelector()).get(0);
		processElement(element, pattern.getChild(), dataArray);

		JsonObject json = new JsonObject();
		json.add(SystemConstants.rootProperty, dataArray);
		return json.toString();
	}

	private void processElement(Element parent, InputPatternDTO pattern, JsonArray jsonArray) {
		if (parent == null || pattern == null) {
			return;
		}
		Elements elements = parent.select(pattern.getCssSelector());
		if (pattern.isRepeated()) {
			Iterator<Element> iterator = elements.iterator();
			while (iterator.hasNext()) {
				Element element = iterator.next();
				JsonObject json = new JsonObject();
				for (String key : pattern.getVariables().keySet()) {
					json.addProperty(key, element.attr(pattern.getVariables().get(key)));
				}
				jsonArray.add(json);
				if (CommonUtils.haveChilds(element)) {
					processElement(element, pattern.getChild(), jsonArray);
				}
			}
		} else {
			Element element = elements.get(0);
			JsonObject json = new JsonObject();
			for (String key : pattern.getVariables().keySet()) {
				json.addProperty(key, element.attr(pattern.getVariables().get(key)));
			}
			jsonArray.add(json);
			jsonArray.add(json);
			if (CommonUtils.haveChilds(element)) {
				processElement(element, pattern.getChild(), jsonArray);
			}
		}
	}
}
