package solutions.egen.app;

import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class RestApplication extends ResourceConfig{
	public RestApplication(){
		System.out.println("febdjf");
		packages("solutions.egen.rest");
		
		register(io.swagger.jaxrs.listing.ApiListingResource.class);
		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setBasePath("/RESTApi/api");
        beanConfig.setResourcePackage("solutions.egen");
        beanConfig.setTitle("RESTApi Documentation");
        beanConfig.setDescription("REST API for the RESTApp");
        beanConfig.setScan(true);
		
	}

}
