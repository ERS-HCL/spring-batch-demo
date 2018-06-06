package org.kmrn.spring.batch.cpc.launcher.web;

import org.kmrn.spring.batch.cpc.launcher.CpcJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CpcJobLauncherController {
	@Autowired
	CpcJobLauncher cpcJobLauncher;

	@RequestMapping("/cpcJob")
	public String handle() throws Exception {
		cpcJobLauncher.start();
		return "ok";
	}
}
