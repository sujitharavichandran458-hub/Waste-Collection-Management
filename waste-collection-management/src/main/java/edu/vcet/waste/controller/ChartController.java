package edu.vcet.waste.controller;

import edu.vcet.waste.repository.WasteCollectionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/charts")
public class ChartController {

    private final WasteCollectionRepository collections;

    public ChartController(WasteCollectionRepository collections) {
        this.collections = collections;
    }

    @GetMapping("/categories")
    public Map<String, Object> categoryChart() {
        return chartData(collections.categoryTotals());
    }

    @GetMapping("/areas")
    public Map<String, Object> areaChart() {
        return chartData(collections.areaTotals());
    }

    @GetMapping("/vehicles")
    public Map<String, Object> vehicleChart() {
        return chartData(collections.vehicleTotals());
    }

    @GetMapping("/statuses")
    public Map<String, Object> statusChart() {
        return chartData(collections.statusTotals());
    }

    private Map<String, Object> chartData(List<Object[]> rows) {
        List<String> labels = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        for (Object[] row : rows) {
            labels.add(String.valueOf(row[0]));
            values.add(row[1]);
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("labels", labels);
        result.put("values", values);

        return result;
    }
}