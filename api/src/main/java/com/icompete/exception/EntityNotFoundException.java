package com.icompete.exception;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 24/11/2016
 */
public class EntityNotFoundException extends Exception {
    /**
     * Name of entity
     */
    private String entityName;

    public EntityNotFoundException(String entityName) {
        this.entityName = entityName;
    }

    public EntityNotFoundException(String entityName, String message) {
        super(message);
        this.entityName = entityName;
    }

    public EntityNotFoundException(String entityName, Throwable cause) {
        super(cause);
        this.entityName = entityName;
    }

    public EntityNotFoundException(String entityName, String message, Throwable cause) {
        super(message, cause);
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }

    @Override
    public String toString() {
        return "EntityNotFoundException{" +
                "entityName='" + entityName + '\'' +
                '}';
    }
}
