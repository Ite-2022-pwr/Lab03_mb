package pwr.ite.bedrylo.lab03.client.enums;

import lombok.Getter;

@Getter
public enum Role {
    BOSS("boss"),
    EMPLOYEE("employee"),
    CLIENT("client");
    
    private final String name;
    
    Role(String name) {
        this.name = name;
    }
    
    public String getRoleByName(String name) {
        for (Role role : Role.values()) {
            if (role.name.equals(name)) {
                return role.name;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
