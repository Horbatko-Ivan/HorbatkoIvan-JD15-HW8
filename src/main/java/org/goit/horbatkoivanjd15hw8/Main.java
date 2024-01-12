package org.goit.horbatkoivanjd15hw8;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.goit.horbatkoivanjd15hw8.entity.Client;
import org.goit.horbatkoivanjd15hw8.services.ClientService;
import org.goit.horbatkoivanjd15hw8.services.Database;
import org.goit.horbatkoivanjd15hw8.services.FlywayMigration;

@SuppressWarnings("checkstyle:MissingJavadocType")
public class Main {

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public static void main(String[] args) {

    new FlywayMigration().flywayMigration();

    Database database = Database.getInstance();
    Connection conn = database.getConnection();
    Logger log = Logger.getAnonymousLogger();

    try {
      System.out.println(new ClientService().create("Ivan"));
      System.out.println(new ClientService().create("Egor"));
    } catch (SQLException e) {
      log.log(Level.SEVERE, "Exception on create: ", e);
    }

    System.out.println(new ClientService().getById(4L));

    new ClientService().deleteById(6L);
    new ClientService().setName(3L, "Eliot");

    try {
      List<Client> clients = new ClientService().listAll();
      System.out.println(clients.toString());

    } catch (SQLException e) {
      log.log(Level.SEVERE, "Exception on listAll : ", e);
    }
    try {
      conn.close();
    } catch (SQLException e) {
      log.log(Level.SEVERE, "Exception on close : ", e);
    }
  }
}
