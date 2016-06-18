package com.html.crawler;

import java.io.IOException;

import com.html.crawler.dto.InputPatternDTO;
import com.html.crawler.processor.InputHtmlPatternProcessor;
import com.html.crawler.processor.ResourceHtmlProcessor;

public class Launcher {

	private static String url = "http://www.snapdeal.com/products/mobiles-mobile-phones/filters/Form_s~Smartphones?sort=plrty";
	private static String outputFilePath = "/Users/MMT6161/output.txt";
	private static String sampleInputFile = "/Users/MMT6161/Documents/sampleInput.html";
	private static String baseUri = "http://www.snapdeal.com/";
	private static String defaultCharSet = "UTF-8";

	public static void main(String[] args) throws IOException {

		InputHtmlPatternProcessor processor = new InputHtmlPatternProcessor();
		InputPatternDTO patternDto = processor.processInputHTML(sampleInputFile, baseUri);

		ResourceHtmlProcessor resourceProcessor = new ResourceHtmlProcessor();
		System.out.println(resourceProcessor.processUrl(url, patternDto));
	}

}
