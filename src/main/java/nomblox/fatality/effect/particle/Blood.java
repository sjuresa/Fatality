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

import net.md_5.bungee.api.ChatColor;
import nomblox.fatality.data.EffectSettingsDto;
import nomblox.fatality.effect.FatalityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;

import java.util.Random;

import static nomblox.fatality.data.Effects.capitalize;
import static nomblox.fatality.effect.EffectConstants.*;

public class Blood extends ParticleEffect implements FatalityEffect {
    public static final String NAME = "Blood";

    public Blood(EffectSettingsDto effectSettingsDto) {
        super(effectSettingsDto, BLOOD_COLORS.length, NAME);
    }

    @Override
    public String getSettingName() {
        if (BLOOD_COLORS[settings.selected] == RANDOM_BLOOD_COLOR) {
            return ChatColor.GOLD + "Random";
        }
        if (BLOOD_COLORS[settings.selected] == SMART_BLOOD_COLOR) {
            return ChatColor.GOLD + "Smart";
        }
        return BLOOD_COLORS[settings.selected].toString() + capitalize(BLOOD_COLORS[settings.selected].getName().toLowerCase().replace("_", " "));
    }

    @Override
    public void playEffect(LivingEntity livingEntity, Player player, Location location) {
        player.getWorld().spawnParticle(org.bukkit.Particle.BLOCK_CRACK, location, 80, getMaterial(livingEntity).createBlockData());
    }

    @Override
    public Material getMenuIcon() {
        return Material.RED_DYE;
    }

    protected Material getMaterial() {
        if (BLOOD_COLORS[settings.selected] == RANDOM_BLOOD_COLOR) {
            Random random = new Random();
            return BLOOD_MATERIAL[random.nextInt(BLOOD_MATERIAL.length - 1) + 1];
        }
        return BLOOD_MATERIAL[settings.selected];
    }

    protected Material getMaterial(LivingEntity entity) {
        if (BLOOD_COLORS[settings.selected] != SMART_BLOOD_COLOR || entity == null) {
            return getMaterial();
        }
        ChatColor bloodColor = ChatColor.RED;
        if (entity instanceof Blaze) {
            bloodColor = ChatColor.YELLOW;
        }
        if (entity instanceof CaveSpider) {
            bloodColor = ChatColor.DARK_GRAY;
        }
        if (entity instanceof Creeper) {
            bloodColor = ChatColor.GREEN;
        }
        if (entity instanceof Drowned) {
            bloodColor = ChatColor.DARK_AQUA;
        }
        if (entity instanceof ElderGuardian) {
            bloodColor = ChatColor.DARK_PURPLE;
        }
        if (entity instanceof EnderDragon) {
            bloodColor = ChatColor.DARK_PURPLE;
        }
        if (entity instanceof Endermite) {
            bloodColor = ChatColor.DARK_PURPLE;
        }
        if (entity instanceof Evoker) {
            bloodColor = ChatColor.BLACK;
        }
        if (entity instanceof Ghast) {
            bloodColor = ChatColor.GRAY;
        }
        if (entity instanceof Giant) {
            bloodColor = ChatColor.GREEN;
        }
        if (entity instanceof Guardian) {
            bloodColor = ChatColor.GOLD;
        }
        if (entity instanceof Hoglin) {
            bloodColor = ChatColor.RED;
        }
        if (entity instanceof Husk) {
            bloodColor = ChatColor.BLACK;
        }
        if (entity instanceof Illusioner) {
            bloodColor = ChatColor.BLUE;
        }
        if (entity instanceof MagmaCube) {
            bloodColor = ChatColor.DARK_RED;
        }
        if (entity instanceof Phantom) {
            bloodColor = ChatColor.DARK_BLUE;
        }
        if (entity instanceof PiglinBrute) {
            bloodColor = ChatColor.GOLD;
        }
        if (entity instanceof Pillager) {
            bloodColor = ChatColor.GRAY;
        }
        if (entity instanceof Ravager) {
            bloodColor = ChatColor.DARK_GRAY;
        }
        if (entity instanceof Shulker) {
            bloodColor = ChatColor.LIGHT_PURPLE;
        }
        if (entity instanceof Silverfish) {
            bloodColor = ChatColor.GRAY;
        }
        if (entity instanceof Skeleton) {
            bloodColor = ChatColor.GRAY;
        }
        if (entity instanceof Slime) {
            bloodColor = ChatColor.GREEN;
        }
        if (entity instanceof Spider) {
            bloodColor = ChatColor.DARK_GRAY;
        }
        if (entity instanceof Stray) {
            bloodColor = ChatColor.WHITE;
        }
        if (entity instanceof Vex) {
            bloodColor = ChatColor.AQUA;
        }
        if (entity instanceof Vindicator) {
            bloodColor = ChatColor.GRAY;
        }
        if (entity instanceof Witch) {
            bloodColor = ChatColor.DARK_PURPLE;
        }
        if (entity instanceof Wither) {
            bloodColor = ChatColor.BLACK;
        }
        if (entity instanceof Zoglin) {
            bloodColor = ChatColor.DARK_GREEN;
        }
        if (entity instanceof Zombie) { // and ZombieVillager
            bloodColor = ChatColor.DARK_GREEN;
        }
        return COLOR_MATERIAL_MAP.get(bloodColor);
    }
}