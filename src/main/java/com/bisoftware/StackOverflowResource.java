package com.bisoftware;

import io.agroal.api.AgroalDataSource;
import io.smallrye.mutiny.Uni;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/stackoverflow")
public class StackOverflowResource {

    @Inject
    AgroalDataSource defaultDataSource;

    @GET
    @Path("randomUserName")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> getRandomUserName() {
        return Uni.createFrom().item(() -> {
            try {
                Statement statement = defaultDataSource.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(
                    "SELECT TOP 1 DisplayName FROM [StackOverflow2010].[dbo].[Users] ORDER BY NEWID()");
                resultSet.next();
                return resultSet.getString(1);
            } catch (Exception e) {
                return e.getMessage();
            }
        });
    }
}
