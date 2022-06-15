package com.davidvelz.diagnosticsystem.objects;

public class SystemModel {
    private String system_id;
    private String name_ES;
    private String description;
    private String icon;

    public SystemModel(){}
    public SystemModel(String system_id, String name_ES, String description, String icon){
        this.system_id = system_id;
        this.name_ES = name_ES;
        this.description = description;
        this.icon = icon;
    }

    public String getSystem_id() {
        return system_id;
    }

    public String getName_ES() {
        return name_ES;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
    public void setSystem_id(String system_id) {
        this.system_id = system_id;
    }

    public void setName_ES(String name_ES) {
        this.name_ES = name_ES;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
