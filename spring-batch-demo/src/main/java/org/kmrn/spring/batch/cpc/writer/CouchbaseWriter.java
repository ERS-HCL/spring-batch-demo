package org.kmrn.spring.batch.cpc.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class CouchbaseWriter<Object> implements ItemWriter<Object> {
	@Override
	public void write(List<? extends Object> objects) throws Exception {
		System.out.println(objects);
		
	}

}
