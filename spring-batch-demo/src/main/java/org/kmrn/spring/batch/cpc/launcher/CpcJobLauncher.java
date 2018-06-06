package org.kmrn.spring.batch.cpc.launcher;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.kmrn.spring.batch.cpc.CpcJob;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CpcJobLauncher {
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	CpcJob cpcJob;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH");

	public void start() throws Exception {
		InputStream offersStream = this.getClass().getResourceAsStream("/offers.txt");
		InputStream productsStream = this.getClass().getResourceAsStream("/products.txt");
		jobLauncher.run(cpcJob.cpcJob(offersStream, productsStream), getJobParameters());
		System.out.println("The time is now " + dateFormat.format(new Date()));
	}
	
	private JobParameters getJobParameters() {
		JobParametersBuilder jobBuilder= new JobParametersBuilder();
		jobBuilder.addString("date", dateFormat.format(new Date()));
		return jobBuilder.toJobParameters();
	}
}
