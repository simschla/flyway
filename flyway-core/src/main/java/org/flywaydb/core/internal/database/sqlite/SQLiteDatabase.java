/*
 * Copyright 2010-2018 Boxfuse GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flywaydb.core.internal.database.sqlite;

import org.flywaydb.core.api.configuration.FlywayConfiguration;
import org.flywaydb.core.api.errorhandler.ErrorHandler;
import org.flywaydb.core.internal.database.Database;
import org.flywaydb.core.internal.database.ExecutableSqlScript;
import org.flywaydb.core.internal.database.SqlScript;
import org.flywaydb.core.internal.exception.FlywayDbUpgradeRequiredException;
import org.flywaydb.core.internal.util.scanner.Resource;

import java.sql.Connection;
import java.sql.Types;

/**
 * SQLite database.
 */
public class SQLiteDatabase extends Database {
    /**
     * Creates a new instance.
     *
     * @param configuration The Flyway configuration.
     * @param connection    The connection to use.
     */
    public SQLiteDatabase(FlywayConfiguration configuration, Connection connection



    ) {
        super(configuration, connection, Types.VARCHAR



        );
    }

    @Override
    protected org.flywaydb.core.internal.database.Connection getConnection(Connection connection, int nullType



    ) {
        return new SQLiteConnection(configuration, this, connection, nullType



        );
    }

    @Override
    protected final void ensureSupported() {
        String version = majorVersion + "." + minorVersion;

        if (majorVersion < 3) {
            throw new FlywayDbUpgradeRequiredException("SQLite", version, "3.7.2");
        }
    }

    @Override
    protected SqlScript doCreateSqlScript(Resource sqlScriptResource, String sqlScriptSource, boolean mixed



    ) {
        return new SQLiteSqlScript(sqlScriptResource, sqlScriptSource, mixed



        );
    }

    public String getDbName() {
        return "sqlite";
    }

    @Override
    protected String doGetCurrentUser() {
        return "";
    }

    @Override
    public boolean supportsDdlTransactions() {
        return true;
    }








    @Override
    public String getBooleanTrue() {
        return "1";
    }

    @Override
    public String getBooleanFalse() {
        return "0";
    }

    @Override
    public String doQuote(String identifier) {
        return "\"" + identifier + "\"";
    }

    @Override
    public boolean catalogIsSchema() {
        return true;
    }

    @Override
    public boolean useSingleConnection() {
        return true;
    }
}