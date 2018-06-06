package org.kmrn.spring.batch.cpc.launcher.scheduler;

import org.kmrn.spring.batch.cpc.launcher.CpcJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CpcSchduler {
	@Autowired
	CpcJobLauncher cpcJobLauncher;
	
	@Scheduled(fixedRate = 30000)
	public void reportCurrentTime() throws Exception {
		cpcJobLauncher.start();
	}
	
}
