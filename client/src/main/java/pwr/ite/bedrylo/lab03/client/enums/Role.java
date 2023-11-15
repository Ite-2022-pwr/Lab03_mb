package pwr.ite.bedrylo.lab03.client.enums;

import lombok.Getter;

@Getter
public enum Role {
    BOSS("kierownik"),
    EMPLOYEE("kontroler"),
    CLIENT("klient");
    
    private final String name;
    
    Role(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
