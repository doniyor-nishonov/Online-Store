package uz.pdp.backend.model;

import java.util.UUID;

public abstract class BaseModel {
    private String id;

    public BaseModel(String id){
        this.id = id;
    }

    public BaseModel() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return this.id;
    }
}
