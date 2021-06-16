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

package nomblox.fatality.data;

public class EffectSettingsDto implements Cloneable {
    public String name;
    public boolean enabled;
    public int selected;

    public EffectSettingsDto() {
    }

    public EffectSettingsDto(String name, EffectDto effectDto) {
        this.name = name;
        enabled = effectDto.enabled;
        selected = effectDto.selected;
    }

    public EffectSettingsDto clone() {
        try {
            return (EffectSettingsDto) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "[" + name + ", " + enabled + ", " + selected + "]";
    }
}