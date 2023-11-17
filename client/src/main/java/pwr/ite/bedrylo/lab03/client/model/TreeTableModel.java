package pwr.ite.bedrylo.lab03.client.model;

import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;
import pwr.ite.bedrylo.lab03.client.dto.TreeDto;

@Getter
@Setter
public class TreeTableModel {
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty diameter = new SimpleStringProperty("");
    private TreeDto treeDto;
    
    public TreeTableModel(TreeDto treeDto) {
        name.set(treeDto.getName());
        diameter.set(Float.toString(treeDto.getDiameter()));
        this.treeDto = treeDto;
    }
    
    public String getName() {
        return name.get();
    }
    
    public String getDiameter() {
        return diameter.get();
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public void setDiameter(String diameter) {
        this.diameter.set(diameter);
    }
    
    public TreeDto getTreeDto() {
        return this.treeDto;
    }
    
    public void setTreeDto(TreeDto treeDto) {
        this.treeDto = treeDto;
    }
    
}
