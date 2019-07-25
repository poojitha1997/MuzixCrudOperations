package com.stackroute.MuzixCrudOperations.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track
{
    @Id
    private int id;
    private String name;
    private String comment;
//    public Track()
//    {}
//
//    public Track(int id, String name, String comment) {
//        this.id = id;
//        this.name = name;
//        this.comment = comment;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//    @Override
//    public String toString() {
//        return "Track{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", comment='" + comment + '\'' +
//                '}';
//    }

}
