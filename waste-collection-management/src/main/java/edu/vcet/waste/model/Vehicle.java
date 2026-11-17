package edu.vcet.waste.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
@Entity @Table(name="vehicles")
public class Vehicle {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Column(nullable=false, unique=true) private String registrationNumber;
 @NotBlank @Column(nullable=false) private String vehicleType;
 @NotNull @DecimalMin("0.1") @Column(nullable=false, precision=10, scale=2) private BigDecimal capacityTonnes;
 @NotBlank @Column(nullable=false) private String availabilityStatus="Available";
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public String getRegistrationNumber(){return registrationNumber;} public void setRegistrationNumber(String v){registrationNumber=v;}
 public String getVehicleType(){return vehicleType;} public void setVehicleType(String v){vehicleType=v;}
 public BigDecimal getCapacityTonnes(){return capacityTonnes;} public void setCapacityTonnes(BigDecimal v){capacityTonnes=v;}
 public String getAvailabilityStatus(){return availabilityStatus;} public void setAvailabilityStatus(String v){availabilityStatus=v;}
}
