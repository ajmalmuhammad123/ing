package com.ing.bank.enums;

/**
 *
 * @author muhammad.ajmal
 */
public enum UserStatus {

    ACTIVE(1),
    IN_ACTIVE(0),
    BLOCKED(3);

    private final int status;

    UserStatus(int status) {
        this.status = status;
    }

    public int getValue() {
        return this.status;
    }

    public static UserStatus build(int id) {

        for (UserStatus userStatus : values()) {
            if (userStatus.status == id) {
                return userStatus;
            }
        }
        return null;
    }
}
