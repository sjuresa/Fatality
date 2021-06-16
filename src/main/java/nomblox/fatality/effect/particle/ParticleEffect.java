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

package nomblox.fatality.effect.particle;

import nomblox.fatality.data.EffectSettingsDto;
import nomblox.fatality.data.ParticleCategory;
import nomblox.fatality.effect.FatalityEffect;
import nomblox.fatality.effect.FatalityEffectBase;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Random;

abstract public class ParticleEffect extends FatalityEffectBase implements FatalityEffect {
    private final Random random = new Random();

    public ParticleEffect(EffectSettingsDto effectSettingsDto, int nrOfSettings, String name) {
        super(ParticleCategory.NAME, effectSettingsDto, nrOfSettings, name);
    }

    @Override
    public String getSettingName() {
        return getName();
    }

    abstract public void playEffect(LivingEntity livingEntity, Player player, Location location);

    protected void spawnParticle(org.bukkit.Particle particle, Player player, Location location, int count, double offset) {
        spawnParticle(particle, player, location, count, offset, null);
    }

    protected void spawnParticle(org.bukkit.Particle particle, Player player, Location location, int count, double offset, Vector velocity) {
        if (velocity == null) {
            player.spawnParticle(particle, location, count, offset, offset, offset, 0, null);
        }
        else {
            for (int i = 0; i < count; i++) {
                Location singleLocation = location.clone().add((random.nextDouble() - 0.5) * offset,
                        (random.nextDouble() - 0.5) * offset,
                        (random.nextDouble() - 0.5) * offset);
                player.spawnParticle(particle, singleLocation, 0, velocity.getX(), velocity.getY(), velocity.getZ(), 1f, null);
            }
        }
    }
}