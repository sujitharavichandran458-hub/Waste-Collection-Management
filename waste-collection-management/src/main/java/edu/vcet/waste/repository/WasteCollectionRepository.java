package edu.vcet.waste.repository;
import edu.vcet.waste.model.WasteCollection;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;
public interface WasteCollectionRepository extends JpaRepository<WasteCollection,Long> {
 @Query("select coalesce(sum(w.quantityKg),0) from WasteCollection w") BigDecimal totalQuantity();
 @Query("select count(w) from WasteCollection w where w.status='Completed'") long completedCount();
 @Query("select w.area.areaName, sum(w.quantityKg) from WasteCollection w group by w.area.areaName order by sum(w.quantityKg) desc")
 List<Object[]> areaTotals();
 @Query("select w.vehicle.registrationNumber, sum(w.quantityKg) from WasteCollection w group by w.vehicle.registrationNumber order by sum(w.quantityKg) desc")
 List<Object[]> vehicleTotals();
 @Query("select w.wasteCategory, sum(w.quantityKg) from WasteCollection w group by w.wasteCategory order by sum(w.quantityKg) desc")
 List<Object[]> categoryTotals();
 @Query("select w.status, count(w) from WasteCollection w group by w.status") List<Object[]> statusTotals();
}
