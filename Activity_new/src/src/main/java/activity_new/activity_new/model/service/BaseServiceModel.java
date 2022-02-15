package activity_new.activity_new.model.service;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class BaseServiceModel {

    private Long id;

    public BaseServiceModel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public BaseServiceModel setId(Long id) {
        this.id = id;
        return this;
    }
}
