package com.aeox.app.common.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pablolm on 23/7/17.
 */
@MappedSuperclass
public class AbstractEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Version
    private long version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
