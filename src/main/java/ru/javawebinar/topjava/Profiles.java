package ru.javawebinar.topjava;

import org.springframework.util.ClassUtils;

import java.time.format.DateTimeFormatter;

public class Profiles {
    public static final DateTimeFormatter HSQLDB_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final String
            JDBC = "jdbc",
            JPA = "jpa",
            DATAJPA = "datajpa";

    public static final String REPOSITORY_IMPLEMENTATION = DATAJPA;

    public static final String
            POSTGRES_DB = "postgres",
            HSQL_DB = "hsqldb";

    public static final String DB_IMPLEMENTATION = HSQL_DB;

    //  Get DB profile depending of DB driver in classpath
    public static String getActiveDbProfile() {
        if (ClassUtils.isPresent("org.postgresql.Driver", null)) {
            return POSTGRES_DB;
        } else if (ClassUtils.isPresent("org.hsqldb.jdbcDriver", null)) {
            return HSQL_DB;
        } else {
            throw new IllegalStateException("Could not find DB driver");
        }
    }

    public static boolean isHsqldbProfile() {
        return HSQL_DB.equals(getActiveDbProfile());
    }
}
