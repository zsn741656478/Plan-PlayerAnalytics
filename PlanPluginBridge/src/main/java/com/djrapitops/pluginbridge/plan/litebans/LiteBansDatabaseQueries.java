package com.djrapitops.pluginbridge.plan.litebans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import litebans.api.Database;
import main.java.com.djrapitops.plan.database.tables.Table;

/**
 * Class responsible for making queries to LiteBans database.
 *
 * @author Rsl1122
 * @since 3.5.0
 */
public class LiteBansDatabaseQueries extends Table{
    private final Database database;

    public LiteBansDatabaseQueries() {
        super("litebans", null, false);
        database = Database.get();
    }
    
    public List<BanObject> getBans() throws SQLException {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = database.prepareStatement("SELECT uuid, reason, banned_by_name, until FROM litebans_bans");
            set = statement.executeQuery();
            List<BanObject> bans = new ArrayList<>();
            while (set.next()) {
                String uuidS = set.getString("uuid");
                UUID uuid = UUID.fromString(uuidS);
                String reason = set.getString("reason");
                String bannedBy = set.getString("banned_by_name");
                long time = set.getLong("until");
                bans.add(new BanObject(uuid, reason, bannedBy, time));
            }
            return bans;
        } finally {
            close(set);
            close(statement);
        }
    }
    
    public List<BanObject> getBans(UUID playerUUID) throws SQLException {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = database.prepareStatement("SELECT uuid, reason, banned_by_name, until FROM litebans_bans WHERE uuid=?");
            statement.setString(1, playerUUID.toString());
            set = statement.executeQuery();
            List<BanObject> bans = new ArrayList<>();
            while (set.next()) {
                String uuidS = set.getString("uuid");
                UUID uuid = UUID.fromString(uuidS);
                String reason = set.getString("reason");
                String bannedBy = set.getString("banned_by_name");
                long time = set.getLong("until");
                bans.add(new BanObject(uuid, reason, bannedBy, time));
            }
            return bans;
        } finally {
            close(set);
            close(statement);
        }
    }

    @Override
    @Deprecated
    public boolean createTable() {
        return false;
    }
}