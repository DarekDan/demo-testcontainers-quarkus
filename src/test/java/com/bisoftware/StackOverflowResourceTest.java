package com.bisoftware;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
class StackOverflowResourceTest {

    DockerImageName sqlImage = DockerImageName.parse("stackoverflow-2010-db").asCompatibleSubstituteFor("mcr.microsoft.com/mssql/server");

    @Container
    public MSSQLServerContainer mssqlserver = new MSSQLServerContainer(sqlImage)
        .acceptLicense();

    @Test
    public void someTestMethod() {
        String url = mssqlserver.getJdbcUrl();
        System.out.println(url);
        assertThat(url.isBlank()).isFalse();
    }

}
