package hoon.sth.bustimely.controller;

import hoon.sth.bustimely.service.BusStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/busStops")
public class BusStopController {
    private final BusStopService busService;

    @Autowired
    public BusStopController(BusStopService busService) {
        this.busService = busService;
    }

    @GetMapping("/searchByRoute")
    public List<Map<String, String>> searchByRoute(@RequestParam String routeName) throws Exception {
        return busService.getArrivalInfoByRoute(routeName);
    }

    @GetMapping("/searchByRouteAndStation")
    public List<Map<String, String>> searchByRouteAndStation(@RequestParam String routeName, @RequestParam String stationName) throws Exception {
        return busService.getArrivalInfoByRouteAndStop(routeName, stationName);
    }
}

