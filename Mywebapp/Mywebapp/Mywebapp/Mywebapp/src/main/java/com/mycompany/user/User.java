package com.mycompany.user;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "users_test")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 45, nullable = false, name = "firstName")
  private String firstName;

  @Column(length = 45, nullable = false, name = "studentId")
  private String studentId;

  @Column(length = 45, nullable = false, name = "className")
  private String className;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + getId() +

            ", firstName='" + getFirstName() + '\'' +
            '}';
  }

}
