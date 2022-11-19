package it.uniroma3.ingdati.xpath.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class ExtractedLabeledDataVO implements Serializable {

    private static final long serialVersionUID = -449963153120712752L;
    private String url;
    private Map<String, Map<String,String>> label2xpathData = new HashMap<>();

}
