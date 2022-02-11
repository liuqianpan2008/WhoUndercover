package org.fenglin;


import java.util.List;

public class Mypaly {
    private long id=0;
    private String name="空";
    private Boolean Isundercover=false;
    private Boolean Isadmin=false;
    private String world="空";
    private String describe="空";
    private Boolean Isviod=true;
    private int viod=0;
    public Boolean getIsviod() {
        return Isviod;
    }

    public void setIsviod(Boolean isviod) {
        Isviod = isviod;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsundercover() {
        return Isundercover;
    }

    public void setIsundercover(Boolean isundercover) {
        Isundercover = isundercover;
    }

    public Boolean getIsadmin() {
        return Isadmin;
    }

    public void setIsadmin(Boolean isadmin) {
        Isadmin = isadmin;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getViod() {
        return viod;
    }

    public void setViod(int viod) {
        this.viod = viod;
    }


}
