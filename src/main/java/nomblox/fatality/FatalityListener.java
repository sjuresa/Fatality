/*
 * Fatality
 * Copyright (C) 2021 Nejc Korošec and Simon Jureša
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package nomblox.fatality;

import nomblox.fatality.data.Effects;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class FatalityListener implements Listener {
    private final FatalityPlugin fatalityPlugin;

    public FatalityListener(FatalityPlugin fatalityPlugin) {
        this.fatalityPlugin = fatalityPlugin;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent entityDeathEvent) {
        if (entityDeathEvent.getEntity().getKiller() == null) {
            return;
        }
        fatality(entityDeathEvent.getEntity(), entityDeathEvent.getEntity().getKiller(), entityDeathEvent.getEntity().getLocation());
    }

    public void fatality(LivingEntity entity, Player player, Location location) {
        String playerId = player.getUniqueId().toString();
        Effects effects = getEffectsForEntity(entity, playerId);
        effects.applyEffects(entity, player, location, fatalityPlugin);
    }

    private Effects getEffectsForEntity(LivingEntity entity, String playerId) {
        if (entity instanceof Monster) {
            return fatalityPlugin.getStorage().getPlayerEffects(playerId).getMonsterSettings();
        }
        if (entity instanceof Player) {
            return fatalityPlugin.getStorage().getPlayerEffects(playerId).getPlayerSettings();
        }
        return fatalityPlugin.getStorage().getPlayerEffects(playerId).getMobSettings();
    }
}