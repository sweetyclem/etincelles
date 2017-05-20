package com.etincelles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class EtincellesApplication extends SpringBootServletInitializer implements CommandLineRunner {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder application ) {
        return application.sources( EtincellesApplication.class );
    }

    public static void main( String[] args ) {
        SpringApplication.run( EtincellesApplication.class, args );
    }

    @Override
    public void run( String... args ) throws Exception {
        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager( entityManager );
            fullTextEntityManager.createIndexer().startAndWait();
            fullTextEntityManager.flushToIndexes();
            System.out.println( "creating index" );
        } catch ( Exception e ) {
            System.out.println( "An error occurred trying to build the search index: " + e.toString() );
        }
        return;
    }
}
