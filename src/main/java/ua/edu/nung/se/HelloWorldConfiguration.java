package ua.edu.nung.se;

import io.dropwizard.core.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class HelloWorldConfiguration extends Configuration {
    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    // конфігурація бд
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

    @JsonProperty("database")
    public DataSourceFactory getDatabase() {
        return database;
    }

    @JsonProperty("database")
    public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }
}
