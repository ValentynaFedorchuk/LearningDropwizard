package ua.edu.nung.se;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import ua.edu.nung.se.dao.FruitDAO;
import ua.edu.nung.se.entity.Fruit;
import ua.edu.nung.se.health.TemplateHealthCheck;
import ua.edu.nung.se.resources.FruitResource;
import ua.edu.nung.se.resources.HelloWorldResource;

public class HelloWorldApplicationStart extends Application<HelloWorldConfiguration> {
    private final HibernateBundle<HelloWorldConfiguration> hibernate = new HibernateBundle<>(
            Fruit.class
    ) {
        @Override
        public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
            return configuration.getDatabase();
        }
    };

    public static void main(String[] args) throws Exception {
        new HelloWorldApplicationStart().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) {
        HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);

        TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        final FruitDAO fruitDAO = new FruitDAO(hibernate.getSessionFactory());

        // реєструю новий ресурс
        final FruitResource fruitResource = new FruitResource(fruitDAO);
        environment.jersey().register(fruitResource);
    }
}
