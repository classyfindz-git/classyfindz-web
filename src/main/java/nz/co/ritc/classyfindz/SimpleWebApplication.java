package nz.co.ritc.classyfindz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class SimpleWebApplication  extends SpringBootServletInitializer {

	    @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(SimpleWebApplication.class);
	    }

	    public static void main(String[] args) throws Exception {
	        SpringApplication.run(SimpleWebApplication.class, args);
	    }

	}