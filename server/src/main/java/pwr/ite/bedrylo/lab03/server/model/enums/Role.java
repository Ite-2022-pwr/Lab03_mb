package pwr.ite.bedrylo.lab03.server.model.enums;

import lombok.Getter;

@Getter
public enum Role {
    BOSS("BOSS"),
    EMPLOYEE("EMPLOYEE"),
    CLIENT("CLIENT");
    
    private final String name;
    
    Role(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
