package hoon.sth.bustimely;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void connectionTest() throws SQLException {
        Connection connection = dataSource.getConnection();
        assertTrue(connection.isValid(1));
        connection.close();
    }
}

