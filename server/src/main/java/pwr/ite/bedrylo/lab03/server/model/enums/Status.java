package pwr.ite.bedrylo.lab03.server.model.enums;

import lombok.Getter;

@Getter
public enum Status {
    CREATED("Utworzony"),
    IN_PROGRESS("W trakcie realizacji"),
    FINISHED("Zakończony");
    
    private final String name;
    
    Status(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}
