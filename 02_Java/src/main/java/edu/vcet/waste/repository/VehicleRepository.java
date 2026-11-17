package edu.vcet.waste.repository;
import edu.vcet.waste.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {}
