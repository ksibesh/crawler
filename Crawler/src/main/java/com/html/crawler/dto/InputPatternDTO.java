package com.html.crawler.dto;

import java.util.Map;

public class InputPatternDTO {

	private boolean root;
	private boolean repeated;
	private String cssSelector;
	private String tagName;
	private InputPatternDTO child;
	private Map<String, String> variables;

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}

	public boolean isRepeated() {
		return repeated;
	}

	public void setRepeated(boolean repeated) {
		this.repeated = repeated;
	}

	public String getCssSelector() {
		return cssSelector;
	}

	public void setCssSelector(String cssSelector) {
		this.cssSelector = cssSelector;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public InputPatternDTO getChild() {
		return child;
	}

	public void setChild(InputPatternDTO child) {
		this.child = child;
	}

	public Map<String, String> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, String> variables) {
		this.variables = variables;
	}

	@Override
	public String toString() {
		return "InputPatternDTO [root=" + root + ", repeated=" + repeated + ", cssSelector=" + cssSelector + ", tagName=" + tagName + ", variables=" + variables + ", child="
				+ child + "]";
	}

}
