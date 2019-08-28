package com.ascending.estate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    /*
    id                   SERIAL NOT NULL,
   name                 VARCHAR(30) not null unique,
   allowed_resource     VARCHAR(200),
   allowed_read         VARCHAR(1) not null default 'N',
   allowed_create       VARCHAR(1) not null default 'N',
   allowed_update       VARCHAR(1) not null default 'N',
   allowed_delete       VARCHAR(1) not null default 'N'
   */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "allowed_resource")
    private String allowedResource;
    @Column(name = "allowed_read")
    private boolean allowedRead;
    @Column(name = "allowed_create")
    private boolean allowedCreate;
    @Column(name = "allowed_update")
    private boolean allowedUpdate;
    @Column(name = "allowed_delete")
    private boolean allowedDelete;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<Agent> agents;

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
    public String getAllowedResource() {
        return allowedResource;
    }
    public void setAllowedResource(String allowedResource) {
        this.allowedResource = allowedResource;
    }
    public boolean isAllowedRead() {
        return allowedRead;
    }
    public void setAllowedRead(boolean allowedRead) {
        this.allowedRead = allowedRead;
    }
    public boolean isAllowedCreate() {
        return allowedCreate;
    }
    public void setAllowedCreate(boolean allowedCreate) {
        this.allowedCreate = allowedCreate;
    }
    public boolean isAllowedUpdate() {
        return allowedUpdate;
    }
    public void setAllowedUpdate(boolean allowedUpdate) {
        this.allowedUpdate = allowedUpdate;
    }
    public boolean isAllowedDelete() {
        return allowedDelete;
    }
    public void setAllowedDelete(boolean allowedDelete) {
        this.allowedDelete = allowedDelete;
    }
    public Set<Agent> getAgents() {
        return agents;
    }
    public void setAgents(Set<Agent> agents) {
        this.agents = agents;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                allowedRead == role.allowedRead &&
                allowedCreate == role.allowedCreate &&
                allowedUpdate == role.allowedUpdate &&
                allowedDelete == role.allowedDelete &&
                name.equals(role.name) &&
                allowedResource.equals(role.allowedResource);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, allowedResource, allowedRead, allowedCreate, allowedUpdate, allowedDelete);
    }
    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        String str = null;
        try {
            str = objectMapper.writeValueAsString(this);
        }
        catch(JsonProcessingException jpe) {
            jpe.printStackTrace();
        }
        return str;
    }

}
