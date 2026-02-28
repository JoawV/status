package com.jow.futstatus.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "championship")
public class Championship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int year;
    private String imgUrl;

    public Championship() {
    }

    public Championship(Long id, String name, int year, String imgUrl) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Championship that = (Championship) o;
        return getYear() == that.getYear() && Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getImgUrl(), that.getImgUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getYear(), getImgUrl());
    }
}
