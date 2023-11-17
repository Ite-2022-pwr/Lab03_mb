package pwr.ite.bedrylo.lab03.server.model.enums;

import lombok.Getter;

@Getter
public enum Status {
    CREATED("Utworzony", "created"),
    IN_PROGRESS("W trakcie realizacji", "in_progress"),
    FINISHED("Zakończony", "finished");
    
    private final String name;
    private final String urlName;
    
    Status(String name, String urlName) {
        this.urlName = urlName;
        this.name = name;
    }
    
    public static Status Status(String urlName) {
        for (Status status : Status.values()) {
            if (status.getUrlName().equals(urlName)) {
                return status;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}
