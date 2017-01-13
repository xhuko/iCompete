package com.icompete.sampledata;

import com.icompete.service.config.ServiceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 14/12/2016
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class SampleDataConfiguration {

    @Inject
    private SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() {
        sampleDataLoadingFacade.loadData();
    }
}
