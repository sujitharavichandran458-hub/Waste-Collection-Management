package edu.vcet.waste.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity @Table(name="waste_collections")
public class WasteCollection {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotNull @Column(nullable=false) private LocalDate collectionDate=LocalDate.now();
 @ManyToOne(optional=false) @JoinColumn(name="area_id") private Area area;
 @ManyToOne(optional=false) @JoinColumn(name="vehicle_id") private Vehicle vehicle;
 @NotBlank @Column(nullable=false) private String wasteCategory;
 @NotNull @DecimalMin("0.01") @Column(nullable=false, precision=10, scale=2) private BigDecimal quantityKg;
 @NotBlank @Column(nullable=false) private String status="Pending";
 @Column(length=500) private String notes;
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public LocalDate getCollectionDate(){return collectionDate;} public void setCollectionDate(LocalDate v){collectionDate=v;}
 public Area getArea(){return area;} public void setArea(Area v){area=v;}
 public Vehicle getVehicle(){return vehicle;} public void setVehicle(Vehicle v){vehicle=v;}
 public String getWasteCategory(){return wasteCategory;} public void setWasteCategory(String v){wasteCategory=v;}
 public BigDecimal getQuantityKg(){return quantityKg;} public void setQuantityKg(BigDecimal v){quantityKg=v;}
 public String getStatus(){return status;} public void setStatus(String v){status=v;}
 public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
}
