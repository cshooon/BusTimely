package hoon.sth.bustimely.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class BusStopServiceTest {

    @Autowired
    private BusStopService busStopService;

    @Test
    public void testGetArrivalInfoByRoute() throws Exception {
        String routeName = "강동02";
        List<Map<String, String>> results = busStopService.getArrivalInfoByRoute(routeName);

        // 결과가 비어있지 않다는 것을 확인합니다.
        assertFalse(results.isEmpty());

        // 첫 번째 결과의 모든 값이 String인지 확인합니다.
        for (String value : results.get(0).values()) {
            assertTrue(value instanceof String);
        }

        // 결과를 출력합니다.
        results.forEach(System.out::println);
    }

    @Test
    public void testGetArrivalInfoByRouteAndStop() throws Exception {
        String routeName = "3323";
        String busStopName = "강동공영차고지";

        List<Map<String, String>> result = busStopService.getArrivalInfoByRouteAndStop(routeName, busStopName);

        assertFalse(result.isEmpty());
    }
}

