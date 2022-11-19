package it.uniroma3.ingdati.xpath.api;

import it.uniroma3.ingdati.xpath.vo.ExtractedLabeledDataVO;

import java.util.List;
import java.util.Map;

public interface ExtractorApi {
    ExtractedLabeledDataVO getLabeledDataFromXpath(String url, Map<String,List<String>> label2xpaths);
}
