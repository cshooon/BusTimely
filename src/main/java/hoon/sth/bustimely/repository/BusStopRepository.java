package hoon.sth.bustimely.repository;

import hoon.sth.bustimely.model.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusStopRepository extends JpaRepository<BusStop, Long> {
    BusStop findFirstByRouteName(String routeName);
    BusStop findFirstByRouteNameAndStationName(String routeName, String stationName);

    // 가는 거 오는 거 2번 필요!!! noUniqueException
}


