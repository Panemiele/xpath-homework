package it.uniroma3.ingdati.xpath.api.impl;

import it.uniroma3.ingdati.xpath.api.ExtractorApi;
import it.uniroma3.ingdati.xpath.domain.ExtractedData;
import it.uniroma3.ingdati.xpath.domain.ExtractedLabeledData;
import it.uniroma3.ingdati.xpath.logic.ExtractorLogic;
import it.uniroma3.ingdati.xpath.utils.csv.CSVUtils;
import it.uniroma3.ingdati.xpath.vo.ExtractedLabeledDataVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ExtractorApiImpl implements ExtractorApi {

    @Override
    public ExtractedLabeledDataVO getLabeledDataFromXpath(String url, Map<String, List<String>> label2xpaths) {
        ExtractorLogic extractorLogic = new ExtractorLogic();
        ExtractedLabeledData extractedData = extractorLogic.extractLabeledData(url, label2xpaths);

        ExtractedLabeledDataVO extractedLabeledDataVO = toExtractedLabeledDataVO(extractedData);
        CSVUtils.appendToDataset(extractedLabeledDataVO);

        return extractedLabeledDataVO;
    }

    /** PRIVATE METHODS */

    private ExtractedLabeledDataVO toExtractedLabeledDataVO(ExtractedLabeledData extractedLabeledData) {
        ExtractedLabeledDataVO extractedLabeledDataVO = new ExtractedLabeledDataVO();
        extractedLabeledDataVO.setUrl(extractedLabeledData.getUrl());
        extractedLabeledDataVO.setLabel2xpathData(extractedLabeledData.getLabel2xpathData());
        return extractedLabeledDataVO;
    }

}
