package org.goit.horbatkoivanjd15hw8.services;

import static org.goit.horbatkoivanjd15hw8.services.Database.URL_DB;

import org.flywaydb.core.Flyway;

@SuppressWarnings("checkstyle:MissingJavadocType")
public class FlywayMigration {

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public void flywayMigration() {

    Flyway flyway = Flyway.configure().baselineOnMigrate(true)
        .dataSource("jdbc:h2:" + URL_DB, "sa", "sa").load();

    flyway.migrate();
  }
}
