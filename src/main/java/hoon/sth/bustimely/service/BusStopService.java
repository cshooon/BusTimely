package hoon.sth.bustimely.service;

import hoon.sth.bustimely.model.BusStop;
import hoon.sth.bustimely.repository.BusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BusStopService {
    private final BusStopRepository busStopRepository;
    private final BusAPIHelper busAPIHelper;

    @Autowired
    public BusStopService(BusStopRepository busStopRepository, BusAPIHelper busAPIHelper) {
        this.busStopRepository = busStopRepository;
        this.busAPIHelper = busAPIHelper;
    }

    public List<Map<String, String>> getArrivalInfoByRoute(String routeName) throws Exception {
        BusStop stop = busStopRepository.findFirstByRouteName(routeName);
        String url = busAPIHelper.buildURLForRoute(stop.getRouteId());
        return busAPIHelper.getAPIResponse(url);
    }

    public List<Map<String, String>> getArrivalInfoByRouteAndStop(String routeName, String busStopName) throws Exception {
        BusStop stop = busStopRepository.findFirstByRouteNameAndStationName(routeName, busStopName);
        String url = busAPIHelper.buildURLForRouteAndStop(stop.getNodeId(), stop.getRouteId(), stop.getOrderNum());
        return busAPIHelper.getAPIResponse(url);
    }
}
