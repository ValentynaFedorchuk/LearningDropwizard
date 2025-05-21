package ua.edu.nung.se.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class DatabaseConfiguration {
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }
}
