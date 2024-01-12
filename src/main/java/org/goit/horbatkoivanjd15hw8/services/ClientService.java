package org.goit.horbatkoivanjd15hw8.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.goit.horbatkoivanjd15hw8.entity.Client;

@SuppressWarnings("checkstyle:MissingJavadocType")
public class ClientService {

  Logger log = Logger.getAnonymousLogger();

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public long create(String name) throws SQLException {
    long id = 0;

    Database database = Database.getInstance();
    Connection conn = database.getConnection();

    try (PreparedStatement statement = conn.prepareStatement(
        "INSERT INTO client (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
      statement.setString(1, name);
      statement.executeUpdate();

      ResultSet rs = statement.getGeneratedKeys();

      if (rs.next()) {
        id = rs.getLong(1);
      }
    } catch (SQLException e) {
      log.log(Level.SEVERE, "Exception on INSERT / SELECT client : ", e);
    }
    return id;
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public String getById(long id) {
    String name = "";
    Database database = Database.getInstance();
    Connection conn = database.getConnection();

    Statement statement;
    try {
      statement = conn.createStatement();
      ResultSet rs = statement.executeQuery("SELECT name FROM client WHERE id = " + (int) id);
      while (rs.next()) {
        name = rs.getString("name");
      }
      statement.close();

    } catch (SQLException e) {
      throw new IllegalArgumentException(e);
    }
    return name;
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public void setName(long id, String name) {
    Database database = Database.getInstance();
    Connection conn = database.getConnection();

    PreparedStatement statement;
    try {
      statement = conn.prepareStatement("UPDATE client SET name = ? WHERE id = ?");
      statement.setString(1, name);
      statement.setInt(2, (int) id);
      statement.addBatch();
      statement.executeBatch();
      statement.close();
    } catch (SQLException e) {
      throw new IllegalArgumentException(e);
    }

  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public void deleteById(long id) {
    Database database = Database.getInstance();
    Connection conn = database.getConnection();

    String sql = "DELETE FROM client WHERE id=?";
    PreparedStatement statement;
    try {
      statement = conn.prepareStatement(sql);
      statement.setInt(1, (int) id);
      statement.executeUpdate();
      statement.close();
    } catch (SQLException e) {
      throw new IllegalArgumentException(e);
    }

  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public List<Client> listAll() throws SQLException {
    List<Client> clientList = new ArrayList<>();
    Database database = Database.getInstance();
    Connection conn = database.getConnection();
    String sql = "SELECT id, name FROM  client";
    try (Statement statement = conn.createStatement()) {
      try (ResultSet rs = statement.executeQuery(sql)) {
        while (rs.next()) {

          int id = rs.getInt("id");
          String name = rs.getString("name");
          clientList.add(new Client(id, name));
        }
      }
    } catch (SQLException e) {
      throw new IllegalArgumentException(e);
    }
    return clientList;
  }

}
