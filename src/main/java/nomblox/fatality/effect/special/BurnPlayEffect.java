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

package nomblox.fatality.effect.special;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BurnPlayEffect extends BukkitRunnable {
    private final LivingEntity clonedEntity;//, clone;
    private int count = 0;
    private final Player player;

    public BurnPlayEffect(LivingEntity clone, Player player) {
        this.clonedEntity = clone;
        this.player = player;
    }

    @Override
    public void run() {
        Location location = clonedEntity.getLocation();
        clonedEntity.teleport(new Location(clonedEntity.getWorld(), location.getX(), location.getY() - clonedEntity.getHeight() / 20, location.getZ(),
                location.getYaw() + 20, location.getPitch() - 4));
        player.spawnParticle(Particle.FLAME, location, 30, 0.5, 0.3, 0.5, 0);
        player.spawnParticle(Particle.REDSTONE, location.add(0, 0.5, 0),
                2, 0.5, 0.3, 0.5, new Particle.DustOptions(Color.BLACK, 2));
        player.spawnParticle(Particle.SMOKE_NORMAL,
                location.add(0, 1, 0).subtract(location), 5, 1, 0, 1);
        if (++count == 25) {
            clonedEntity.remove();
            cancel();
        }
    }
}