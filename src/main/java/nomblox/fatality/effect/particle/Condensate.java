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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Condensate extends ParticleEffect implements FatalityEffect {
    public static final String NAME = "Condensate";

    public Condensate(EffectSettingsDto effectSettingsDto) {
        super(effectSettingsDto, 1, NAME);
    }

    @Override
    public Material getMenuIcon() {
        return Material.SPLASH_POTION;
    }

    @Override
    public void playEffect(LivingEntity livingEntity, Player player, Location location) {
        spawnParticle(Particle.DOLPHIN, player, location, 50, 0.5);
    }
}