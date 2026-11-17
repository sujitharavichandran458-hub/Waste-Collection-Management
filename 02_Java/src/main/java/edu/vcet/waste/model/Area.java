package edu.vcet.waste.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity @Table(name="areas")
public class Area {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Column(nullable=false, unique=true) private String areaName;
 @NotBlank @Column(nullable=false) private String location;
 @NotNull @Min(1) private Integer population;
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public String getAreaName(){return areaName;} public void setAreaName(String v){areaName=v;}
 public String getLocation(){return location;} public void setLocation(String v){location=v;}
 public Integer getPopulation(){return population;} public void setPopulation(Integer v){population=v;}
}
