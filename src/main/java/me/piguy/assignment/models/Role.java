package me.piguy.assignment.models;

import java.io.Serializable;

public enum Role implements Serializable {
    IT("IT Technician"),
    Sales;

    public final String displayName;
    Role(String displayName) {
        this.displayName = displayName;
    }
    Role () {
        this.displayName = this.toString();
    }

}
