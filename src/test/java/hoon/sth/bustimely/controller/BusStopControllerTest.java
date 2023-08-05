package hoon.sth.bustimely.controller;

import hoon.sth.bustimely.service.BusStopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BusStopController.class)
public class BusStopControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusStopService busStopService;

    @Test
    public void testSearchByRoute() throws Exception {
        String routeName = "강동02";

        Map<String, String> mockResult = Collections.singletonMap("testKey", "testValue");
        when(busStopService.getArrivalInfoByRoute(routeName)).thenReturn(Collections.singletonList(mockResult));

        mockMvc.perform(get("/busStops/searchByRoute")
                        .param("routeName", routeName)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'testKey':'testValue'}]"));

        verify(busStopService, times(1)).getArrivalInfoByRoute(routeName);
    }

    @Test
    public void testSearchByRouteAndStation() throws Exception {
        String routeName = "강동02";
        String stationName = "가래여울";

        Map<String, String> mockResult = Collections.singletonMap("testKey", "testValue");
        when(busStopService.getArrivalInfoByRouteAndStop(routeName, stationName)).thenReturn(Collections.singletonList(mockResult));

        mockMvc.perform(get("/busStops/searchByRouteAndStation")
                        .param("routeName", routeName)
                        .param("stationName", stationName)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'testKey':'testValue'}]")); // 기대하는 결과를 JSON 배열로 변경

        verify(busStopService, times(1)).getArrivalInfoByRouteAndStop(routeName, stationName);
    }

}
