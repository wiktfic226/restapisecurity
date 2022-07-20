package pl.fis.restapisecurity.enums;

public enum Permission {
    USER_READ("user:read"),
    USER_EDIT("user:edit"),
    ADMIN("admin");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
