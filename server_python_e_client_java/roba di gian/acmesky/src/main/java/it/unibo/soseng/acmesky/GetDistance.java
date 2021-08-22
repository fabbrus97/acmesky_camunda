package it.unibo.soseng.acmesky;

import camundajar.impl.scala.runtime.Static;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import io.swagger.geoprovider.*;
import io.swagger.geoprovider.auth.*;
import io.swagger.client.model.*;
import io.swagger.geoprovider.geo_client.RisorseApi;

import javax.inject.Inject;

import java.io.File;
import java.util.*;
import io.swagger.geoprovider.*;

public class GetDistance implements JavaDelegate{

	@Inject
	GetDistanceService getDistanceService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		getDistanceService.service();

	}

}
