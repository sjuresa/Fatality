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

import nomblox.fatality.data.EffectSettingsDto;
import nomblox.fatality.data.SpecialCategory;
import nomblox.fatality.effect.FatalityEffect;
import nomblox.fatality.effect.FatalityEffectBase;
import org.bukkit.Material;

public class Burn extends FatalityEffectBase implements FatalityEffect {
    public static final String NAME = "Burn";

    public Burn(EffectSettingsDto effectSettingsDto) {
        super(SpecialCategory.NAME, effectSettingsDto, 1, NAME);
    }

    @Override
    public Material getMenuIcon() {
        return Material.CAMPFIRE;
    }
}