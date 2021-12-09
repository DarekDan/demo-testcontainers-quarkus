package com.bisoftware;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
class StackOverflowResourceTest {

    static DockerImageName sqlImage = DockerImageName.parse("stackoverflow-2010-db").asCompatibleSubstituteFor("mcr.microsoft.com/mssql/server");

    @Container
    static MSSQLServerContainer mssqlserver = new MSSQLServerContainer(sqlImage)
        .acceptLicense();

    @Test
    public void someTestMethod() throws SQLException {
        String url = mssqlserver.getJdbcUrl();
        System.out.println(url);
        final ResultSet resultSet = mssqlserver.createConnection("").prepareStatement("SELECT GETDATE()").executeQuery();
        resultSet.next();
        System.out.println(resultSet.getObject(1));
        assertThat(url.isBlank()).isFalse();
    }

}
