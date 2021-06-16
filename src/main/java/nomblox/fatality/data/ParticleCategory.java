/*
 * Fatality
 * Copyright (C) 2021 Nejc Korošec and Simon Jureša
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package nomblox.fatality.data;

import nomblox.fatality.effect.particle.ParticleEffect;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;

public class ParticleCategory extends Category {
    public static final String NAME = "Particle";

    public ParticleCategory(List<EffectSettingsDto> settings) {
        super(settings, NAME);
    }

    public void apply(LivingEntity livingEntity, Player player, Location location, String entity) {
        ParticleEffect particleEffect = (ParticleEffect) getRandomActiveEffect(player, entity);
        if (particleEffect != null) {
            particleEffect.playEffect(livingEntity, player, location);
        }
    }
}