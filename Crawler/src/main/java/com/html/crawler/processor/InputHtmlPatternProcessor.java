package com.html.crawler.processor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.html.crawler.constants.SystemConstants;
import com.html.crawler.dto.InputPatternDTO;
import com.html.crawler.utils.CommonUtils;
import com.html.crawler.utils.StringUtils;

public class InputHtmlPatternProcessor {

	private static String defaultCharset = "UTF-8";

	public InputPatternDTO processInputHTML(String filePath, String baseUri, String charset) throws IOException {

		File file = new File(filePath);
		if (StringUtils.isEmpty(charset)) {
			charset = defaultCharset;
		}

		Document doc = Jsoup.parse(file, charset, baseUri);

		Element rootElement = doc.select(SystemConstants.rootElementSelector).get(0);
		InputPatternDTO data = processElement(rootElement);
		return data;
	}

	public InputPatternDTO processInputHTML(String filePath, String baseUri) throws IOException {
		return processInputHTML(filePath, baseUri, null);
	}

	private InputPatternDTO processElement(Element element) {
		InputPatternDTO data = new InputPatternDTO();
		data.setRoot(CommonUtils.isRootElement(element));
		data.setRepeated(CommonUtils.isRepeated(element));
		data.setTagName(element.tagName());
		data.setCssSelector(generateSelector(element));
		if (CommonUtils.haveChilds(element)) {
			Iterator<Element> iterator = element.children().iterator();
			while (iterator.hasNext()) {
				data.setChild(processElement(iterator.next()));
			}
		}

		Map<String, String> variableMap = new HashMap<>();
		for (Attribute attr : element.attributes().asList()) {
			if (StringUtils.getNullSafeValue(attr.getValue()).trim().startsWith(SystemConstants.variableConstants)) {
				variableMap.put(attr.getValue().trim().substring(SystemConstants.variableConstants.length()), attr.getKey());
			}
		}
		data.setVariables(variableMap);
		return data;
	}

	private String generateSelector(Element element) {
		StringBuilder selectorPattern = new StringBuilder(element.tagName());
		String tagId = null;
		String tagClass = null;
		for (Attribute attr : element.attributes().asList()) {
			if (SystemConstants.idConstant.equals(attr.getKey())) {
				tagId = attr.getValue();
				break;
			} else if (SystemConstants.classConstant.equals(attr.getKey())) {
				tagClass = attr.getValue();
			}
		}

		if (StringUtils.isEmpty(tagId) && StringUtils.isEmpty(tagClass)) {
			return selectorPattern.toString();
		} else if (StringUtils.isNotEmpty(tagId)) {
			selectorPattern.append(SystemConstants.idCssSelector).append(tagId.trim());
		} else {
			selectorPattern.append(SystemConstants.classCssSelector).append(tagClass.trim());
		}
		return selectorPattern.toString();
	}
}
