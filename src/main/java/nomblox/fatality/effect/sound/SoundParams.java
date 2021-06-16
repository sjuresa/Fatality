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

package nomblox.fatality.effect.sound;

import org.bukkit.Sound;

public class SoundParams {
    public Sound sound;
    public float pitch;
    /**
     * delay in ticks
     */
    public long delay;

    /**
     *
     * @param sound sound to play
     * @param pitch pitch
     * @param delay pause before new sounds may be played in seconds
     */
    public SoundParams(Sound sound, float pitch, double delay) {
        this.sound = sound;
        this.pitch = pitch;
        this.delay = (long)(delay*20);
    }
}