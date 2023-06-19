package com.elsunhoty.rulerpicker.lib;

public enum RulerGravity {
    TOP(0), CENTER(1), BOTTOM(2);

    private int id; // Could be other data type besides int

    private RulerGravity(int id) {
        this.id = id;
    }

    public static RulerGravity parse(int id) {
        for (RulerGravity type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    private int getId() {
        return id;
    }

}
