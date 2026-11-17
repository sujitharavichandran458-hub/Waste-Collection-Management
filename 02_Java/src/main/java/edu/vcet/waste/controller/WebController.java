package edu.vcet.waste.controller;

import edu.vcet.waste.model.*;
import edu.vcet.waste.repository.*;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class WebController {

    private final AreaRepository areas;
    private final VehicleRepository vehicles;
    private final WasteCollectionRepository collections;

    public WebController(
            AreaRepository a,
            VehicleRepository v,
            WasteCollectionRepository c) {

        areas = a;
        vehicles = v;
        collections = c;
    }

    // DASHBOARD
    @GetMapping("/")
    public String dashboard(Model m) {

        m.addAttribute("areaCount", areas.count());
        m.addAttribute("vehicleCount", vehicles.count());
        m.addAttribute("collectionCount", collections.count());
        m.addAttribute("totalKg", collections.totalQuantity());
        m.addAttribute("completedCount", collections.completedCount());

        m.addAttribute("recent", collections.findAll(
                org.springframework.data.domain.Sort.by(
                        org.springframework.data.domain.Sort.Direction.DESC,
                        "collectionDate"
                )
        ));

        m.addAttribute("areaTotals", collections.areaTotals());
        m.addAttribute("categoryTotals", collections.categoryTotals());

        return "index";
    }

    // AREAS
    @GetMapping("/areas")
    public String areas(Model m) {

        m.addAttribute("areas", areas.findAll());
        m.addAttribute("area", new Area());

        return "areas";
    }

    @PostMapping("/areas")
    public String saveArea(
            @Valid @ModelAttribute Area area,
            BindingResult br,
            Model m,
            RedirectAttributes ra) {

        if (br.hasErrors()) {
            m.addAttribute("areas", areas.findAll());
            return "areas";
        }

        areas.save(area);

        ra.addFlashAttribute("message", "Area saved.");

        return "redirect:/areas";
    }

    @PostMapping("/areas/{id}/delete")
    public String deleteArea(
            @PathVariable Long id,
            RedirectAttributes ra) {

        try {
            areas.deleteById(id);
            ra.addFlashAttribute("message", "Area deleted.");

        } catch (Exception e) {
            ra.addFlashAttribute(
                    "error",
                    "Cannot delete an area that has collection records."
            );
        }

        return "redirect:/areas";
    }

    // VEHICLES
    @GetMapping("/vehicles")
    public String vehicles(Model m) {

        m.addAttribute("vehicles", vehicles.findAll());
        m.addAttribute("vehicle", new Vehicle());

        return "vehicles";
    }

    @PostMapping("/vehicles")
    public String saveVehicle(
            @Valid @ModelAttribute Vehicle vehicle,
            BindingResult br,
            Model m,
            RedirectAttributes ra) {

        if (br.hasErrors()) {
            m.addAttribute("vehicles", vehicles.findAll());
            return "vehicles";
        }

        vehicles.save(vehicle);

        ra.addFlashAttribute("message", "Vehicle saved.");

        return "redirect:/vehicles";
    }

    @PostMapping("/vehicles/{id}/delete")
    public String deleteVehicle(
            @PathVariable Long id,
            RedirectAttributes ra) {

        try {
            vehicles.deleteById(id);
            ra.addFlashAttribute("message", "Vehicle deleted.");

        } catch (Exception e) {
            ra.addFlashAttribute(
                    "error",
                    "Cannot delete a vehicle used in collection records."
            );
        }

        return "redirect:/vehicles";
    }

    // COLLECTIONS PAGE
    @GetMapping("/collections")
    public String collections(Model m) {

        m.addAttribute(
                "collections",
                collections.findAll(
                        org.springframework.data.domain.Sort.by(
                                org.springframework.data.domain.Sort.Direction.DESC,
                                "collectionDate"
                        )
                )
        );

        m.addAttribute("areas", areas.findAll());
        m.addAttribute("vehicles", vehicles.findAll());
        m.addAttribute("collection", new WasteCollection());

        return "collections";
    }

    // SAVE COLLECTION
    @PostMapping("/collections")
    public String saveCollection(
            @Valid @ModelAttribute WasteCollection collection,
            BindingResult br,
            Model m,
            RedirectAttributes ra) {

        if (br.hasErrors()
                || collection.getArea() == null
                || collection.getVehicle() == null
                || collection.getVehicle().getId() == null) {

            m.addAttribute("collections", collections.findAll());
            m.addAttribute("areas", areas.findAll());
            m.addAttribute("vehicles", vehicles.findAll());

            return "collections";
        }

        // Retrieve complete vehicle from database
        Long vehicleId = collection.getVehicle().getId();

        Vehicle selectedVehicle =
                vehicles.findById(vehicleId).orElse(null);

        if (selectedVehicle == null
                || selectedVehicle.getCapacityTonnes() == null) {

            ra.addFlashAttribute(
                    "error",
                    "Vehicle capacity is missing."
            );

            return "redirect:/collections";
        }

        // Set complete vehicle object
        collection.setVehicle(selectedVehicle);

        // Convert tonnes into kilograms
        BigDecimal maxCapacity =
                selectedVehicle.getCapacityTonnes()
                        .multiply(new BigDecimal("1000"));

        // Check vehicle capacity
        if (collection.getQuantityKg().compareTo(maxCapacity) > 0) {

            br.rejectValue(
                    "quantityKg",
                    "capacity",
                    "Quantity exceeds the selected vehicle's capacity."
            );

            m.addAttribute("collections", collections.findAll());
            m.addAttribute("areas", areas.findAll());
            m.addAttribute("vehicles", vehicles.findAll());

            return "collections";
        }

        // Save collection
        collections.save(collection);

        ra.addFlashAttribute(
                "message",
                "Collection activity recorded."
        );

        return "redirect:/collections";
    }

    // UPDATE COLLECTION STATUS
    @PostMapping("/collections/{id}/status")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam String status,
            RedirectAttributes ra) {

        if (!List.of(
                "Pending",
                "In Progress",
                "Completed"
        ).contains(status)) {

            ra.addFlashAttribute("error", "Invalid status.");

            return "redirect:/collections";
        }

        collections.findById(id).ifPresent(w -> {
            w.setStatus(status);
            collections.save(w);
        });

        ra.addFlashAttribute(
                "message",
                "Collection status updated."
        );

        return "redirect:/collections";
    }

    // DELETE COLLECTION
    @PostMapping("/collections/{id}/delete")
    public String deleteCollection(@PathVariable Long id) {

        collections.deleteById(id);

        return "redirect:/collections";
    }

    // REPORTS
    @GetMapping("/reports")
    public String reports(Model m) {

        m.addAttribute("areaTotals", collections.areaTotals());
        m.addAttribute("vehicleTotals", collections.vehicleTotals());
        m.addAttribute("categoryTotals", collections.categoryTotals());
        m.addAttribute("statusTotals", collections.statusTotals());

        m.addAttribute("totalKg", collections.totalQuantity());
        m.addAttribute("completedCount", collections.completedCount());

        return "reports";
    }
}
