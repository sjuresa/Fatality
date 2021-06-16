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

package nomblox.fatality.effect.text;

import nomblox.fatality.data.EffectSettingsDto;
import nomblox.fatality.effect.FatalityEffect;
import org.bukkit.Material;

import static nomblox.fatality.effect.EffectConstants.*;

public class Terminator extends Text implements FatalityEffect {
    public static final String NAME = "Terminator";

    public Terminator() {
        super(null, NAME);
    }

    public Terminator(EffectSettingsDto effectSettingsDto) {
        super(effectSettingsDto, NAME);
    }

    @Override
    public String getText() {
        switch (random.nextInt(3)) {
            case 0:
                return first();
            case 1:
                return second();
            default:
                return third();
        }
    }

    private String first() {
        return getColor() + TERMINATOR_POOL_1_1[random.nextInt(TERMINATOR_POOL_1_1.length)] + ": "
                + TERMINATOR_POOL_1_2[random.nextInt(TERMINATOR_POOL_1_2.length)] + ".";
    }

    private String second() {
        return getColor() + TERMINATOR_POOL_2_1[random.nextInt(TERMINATOR_POOL_2_1.length)] + ": "
                + TERMINATOR_POOL_2_2[random.nextInt(TERMINATOR_POOL_2_2.length)] + " "
                + TERMINATOR_POOL_1_1[random.nextInt(TERMINATOR_POOL_1_1.length)] + ". Status: Completed.";
    }

    private String third() {
        return getColor() + TERMINATOR_POOL_3[random.nextInt(TERMINATOR_POOL_3.length)];
    }

    @Override
    public Material getMenuIcon() {
        return Material.IRON_HELMET;
    }
}