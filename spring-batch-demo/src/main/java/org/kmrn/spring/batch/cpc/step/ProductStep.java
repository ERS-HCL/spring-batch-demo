package org.kmrn.spring.batch.cpc.step;

import org.kmrn.spring.batch.cpc.bean.OfferData;
import org.kmrn.spring.batch.cpc.writer.CouchbaseWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ProductStep {
	@Autowired
	private ItemProcessor<? super OfferData, ? extends String> jsonProcessor;
	@Autowired
	private CouchbaseWriter<Object> couchbaseWriter;
	
	public FlatFileItemReader<OfferData> reader(Resource in) throws Exception {

		return new FlatFileItemReaderBuilder<OfferData>().name("file-reader").resource(in).targetType(OfferData.class)
				.linesToSkip(1).delimited().delimiter(",").names(new String[] { "offerId", "description" }).build();
	}
	
	public ItemProcessor<? super OfferData, ? extends String> processor() {
		return jsonProcessor;
	}

	public CouchbaseWriter<Object> writer() {
		return couchbaseWriter;
	}
}
