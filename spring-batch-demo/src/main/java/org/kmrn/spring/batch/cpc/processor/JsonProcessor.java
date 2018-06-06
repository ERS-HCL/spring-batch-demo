package org.kmrn.spring.batch.cpc.processor;

import org.kmrn.spring.batch.cpc.bean.OfferData;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonProcessor implements ItemProcessor<OfferData, String> {
	
	private static final ObjectMapper om = new ObjectMapper();
	
	@Override
	public String process(OfferData object) throws Exception {
		return om.writeValueAsString(object);
	}
}