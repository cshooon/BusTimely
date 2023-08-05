package hoon.sth.bustimely.repository;

import hoon.sth.bustimely.model.BusStop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BusStopRepositoryTest {

    @Autowired
    private BusStopRepository busStopRepository;

    @Test
    public void testFindByRouteName() {
        // 메서드 테스트
        BusStop found = busStopRepository.findFirstByRouteName("강동02");
        System.out.println(found);
        assertThat(found).isNotNull();
        assertThat(found.getRouteId()).isEqualTo("124900003");
    }

    @Test
    public void testFindByRouteNameAndStation_Name() {
        // 메서드 테스트
        BusStop found = busStopRepository.findFirstByRouteNameAndStationName("N13B", "복정역1번출구");

        assertThat(found).isNotNull();
        assertThat(found.getRouteId()).isEqualTo("124000035");
        assertThat(found.getNodeId()).isEqualTo("123000215");
    }
}
