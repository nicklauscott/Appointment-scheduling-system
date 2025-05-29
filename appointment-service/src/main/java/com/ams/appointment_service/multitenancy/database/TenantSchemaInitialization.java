package com.ams.appointment_service.multitenancy.database;

//import org.flywaydb.core.Flyway;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class TenantSchemaInitialization {

//    public static void initializeSchema(DataSource dataSource) {
//        Flyway flyway = Flyway.configure()
//                .dataSource(dataSource)
//                .locations("classpath:db/migration")
//                .load();
//        flyway.migrate();
//    }

    public static void initializeSchema(DataSource dataSource) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = Files.readString(Paths.get("schema.sql"));
            log.info("initializeSchema...");
            Statement stmt = conn.createStatement();
            stmt.execute(sql); // For simple scripts. For multiple statements, use a parser
            log.info("initializeSchema complete");
        } catch (SQLException | IOException e) {
            log.info("\n\nError initializing schema with file {}: \n\n", e.getMessage());
            //throw new RuntimeException(e);
        }
    }

}

