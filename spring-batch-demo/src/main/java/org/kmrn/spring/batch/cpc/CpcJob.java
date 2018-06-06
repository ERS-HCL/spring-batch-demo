package org.kmrn.spring.batch.cpc;

import java.io.InputStream;

import org.kmrn.spring.batch.cpc.bean.OfferData;
import org.kmrn.spring.batch.cpc.step.OfferStep;
import org.kmrn.spring.batch.cpc.step.ProductStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.kmrn.spring.batch.cpc.processor.JsonProcessor;

@Configuration
@Component
@EnableBatchProcessing
public class CpcJob {
	
	@Autowired
    private JobBuilderFactory jbf;
    @Autowired
    private StepBuilderFactory sbf;
    @Autowired
    OfferStep offerStep;
    @Autowired
    ProductStep productStep;
    
	public Job cpcJob(InputStream offersStream, InputStream productsStream) throws Exception {
		
		Step os = sbf.get("offer-step")
				.<OfferData, String>chunk(100)
				.reader(offerStep.reader(new InputStreamResource(offersStream)))
				.processor(offerStep.processor())
				.writer(offerStep.writer())
				.build();
		Step ps = sbf.get("product-step").<OfferData, String>chunk(100)
				.reader(productStep.reader(new InputStreamResource(productsStream)))
				.processor(productStep.processor())
				.writer(productStep.writer())
				.build();
		
		return jbf.get("etl")
				.incrementer(new RunIdIncrementer())
				.start(os)
				.next(ps)
				.build();
	}
	
}
