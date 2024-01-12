package org.goit.horbatkoivanjd15hw8.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("checkstyle:MissingJavadocType")
@Data
@NoArgsConstructor
@Builder
public class Client {

  private int id;
  private String name;

  public Client(int id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Client{"
        + "id=" + id
        + ", name='" + name + '\''
        + "}";
  }
}
