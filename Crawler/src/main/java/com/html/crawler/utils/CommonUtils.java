package com.html.crawler.utils;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;

import com.html.crawler.constants.SystemConstants;

public class CommonUtils {

	private static String varableConstants = "$";

	public static boolean isVariable(Attribute attr) {
		if (StringUtils.getNullSafeValue(attr.getValue()).trim().startsWith(varableConstants)) {
			return true;
		}
		return false;
	}

	public static boolean isRepeated(Element element) {
		String value = element.attr(SystemConstants.repeatedAttr);
		if (StringUtils.isEmpty(value) || !Boolean.parseBoolean(value)) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isRootElement(Element element) {
		String value = element.attr(SystemConstants.rootElementAttr);
		if (StringUtils.isEmpty(value) || !Boolean.parseBoolean(value)) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean haveChilds(Element element) {
		return !element.children().isEmpty();
	}

	public static boolean haveNoChilds(Element element) {
		return !haveChilds(element);
	}
}
