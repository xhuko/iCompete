package com.icompete.enums;

/**
 * @author Sekan
 */
public enum EventState {
    NOT_STARTED, ONGOING, FINISHED;

    public String toString() {
        return this.name();
    }
}
