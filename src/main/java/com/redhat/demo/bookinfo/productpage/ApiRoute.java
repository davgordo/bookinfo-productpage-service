package com.redhat.demo.bookinfo.productpage;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

@ApplicationScoped
public class ApiRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration().bindingMode(RestBindingMode.json)
                .bindingPackageScan("com.redhat.demo.bookinfo.productpage");
        
        
        rest().openApi().specification("productpage-api.json").missingOperation("ignore");
        

        
        from("direct:sampleOperationId")
                .removeHeaders("*")
                .to("rest-openapi:details-api.json#getProductDetails?host={{openapi.client.details.host}}");
        
    }
}
