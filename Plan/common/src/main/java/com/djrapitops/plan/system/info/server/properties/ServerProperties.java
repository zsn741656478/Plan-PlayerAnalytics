/*
 *  This file is part of Player Analytics (Plan).
 *
 *  Plan is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License v3 as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Plan is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Plan. If not, see <https://www.gnu.org/licenses/>.
 */
package com.djrapitops.plan.system.info.server.properties;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

/**
 * Class responsible for holding server variable values that do not change
 * without a reload.
 *
 * @author Rsl1122
 */
public abstract class ServerProperties {

    private final String id;
    private final String name;
    private final int port;
    private final String version;
    private final String implVersion;
    private final Supplier<String> ip;
    private final int maxPlayers;

    private final IntSupplier onlinePlayers;

    protected ServerProperties(
            String id,
            String name,
            int port,
            String version,
            String implVersion,
            Supplier<String> ip,
            int maxPlayers,
            IntSupplier onlinePlayers
    ) {
        this.id = id;
        this.name = name;
        this.port = port;
        this.version = version;
        this.implVersion = implVersion;
        this.ip = ip;
        this.maxPlayers = maxPlayers;
        this.onlinePlayers = onlinePlayers;
    }

    /**
     * Ip string in server.properties.
     *
     * @return the ip.
     */
    public String getIp() {
        return ip.get();
    }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }

    public String getVersion() {
        return version;
    }

    public String getImplVersion() {
        return implVersion;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public String getServerId() {
        return id;
    }

    public int getOnlinePlayers() {
        return onlinePlayers.getAsInt();
    }
}
