package com.wargame.wargame.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "player")
public class Player implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    private String name;

    public Player() {}

    private Player(Builder builder) {
        this.name = builder.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder {
        @JsonProperty("name")
        private String name;

        public Builder setName(String username) {
            this.name = username;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
}
