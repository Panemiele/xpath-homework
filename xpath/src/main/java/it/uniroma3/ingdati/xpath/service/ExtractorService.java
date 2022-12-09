package it.uniroma3.ingdati.xpath.service;

import it.uniroma3.ingdati.xpath.vo.ExtractedLabeledDataVO;

import java.util.List;
import java.util.Map;

public interface ExtractorService {
    ExtractedLabeledDataVO getLabeledDataFromXpath(String url, Map<String,List<String>> label2xpaths);
}
