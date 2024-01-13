package org.goit.horbatkoivanjd15hw8.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("checkstyle:MissingJavadocType")
public class Database {

  public static final String URL_DB
      = "./src/main/resources/db/hw8";
  private static final Database INSTANCE = new Database();
  private Connection connection;

  private Database() {
    try {
      Class.forName("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:" + URL_DB, "sa", "sa");
    } catch (ClassNotFoundException | SQLException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static Database getInstance() {
    return INSTANCE;
  }

  public Connection getConnection() {
    return connection;
  }
}
