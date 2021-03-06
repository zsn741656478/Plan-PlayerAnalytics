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
package com.djrapitops.plan.utilities.html.graphs.pie;

import com.djrapitops.plan.data.time.WorldTimes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ServerPreferencePie extends Pie {

    ServerPreferencePie(Map<UUID, String> serverNames, Map<UUID, WorldTimes> serverWorldTimes) {
        super(turnToSlices(serverNames, serverWorldTimes));
    }

    private static List<PieSlice> turnToSlices(Map<UUID, String> serverNames, Map<UUID, WorldTimes> serverWorldTimes) {
        List<PieSlice> slices = new ArrayList<>();

        for (Map.Entry<UUID, WorldTimes> server : serverWorldTimes.entrySet()) {
            UUID serverUUID = server.getKey();
            WorldTimes worldTimes = server.getValue();

            String serverName = serverNames.getOrDefault(serverUUID, "Unknown");
            long num = worldTimes.getTotal();

            slices.add(new PieSlice(serverName, num));
        }

        return slices;
    }
}
