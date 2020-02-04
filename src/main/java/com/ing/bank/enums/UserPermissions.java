package com.ing.bank.enums;

/**
 *
 * @author muhammad.ajmal
 */
public enum UserPermissions {

  USER("USER"),
  ADMIN("ADMIN");

  private final String permissions;

  UserPermissions(String status) {
    this.permissions = status;
  }

  public String getValue() {
    return this.permissions;
  }
}
