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

import nomblox.fatality.FatalityPlugin;
import nomblox.fatality.effect.FatalityEffect;
import nomblox.fatality.effect.special.Burn;
import nomblox.fatality.effect.special.BurnPlayEffect;
import nomblox.fatality.effect.special.FountainPlayEffect;
import nomblox.fatality.effect.special.ItemFountain;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpecialCategory extends Category {
    public static final String NAME = "Special";

    public SpecialCategory(List<EffectSettingsDto> settings) {
        super(settings, NAME);
    }

    public void apply(LivingEntity diedEntity, Player player, String entity, FatalityPlugin fatalityPlugin) {
        FatalityEffect fatalityEffect = getRandomActiveEffect(player, entity);
        if (fatalityEffect == null) {
            return;
        }
        if (fatalityEffect instanceof Burn) {
            new BurnPlayEffect(cloneLivingEntity(diedEntity), player).runTaskTimer(fatalityPlugin, 1, 3);
        }
        if (fatalityEffect instanceof ItemFountain) {
            new FountainPlayEffect(diedEntity.getLocation(), player, ((ItemFountain) fatalityEffect).getMaterial())
                    .runTaskTimer(fatalityPlugin, 1, 5);
        }
        if (!(diedEntity instanceof Player)) {
            diedEntity.remove();
        }
    }

    @NotNull
    private LivingEntity cloneLivingEntity(LivingEntity diedEntity) {
        EntityType cloneType = diedEntity.getType();
        if (diedEntity instanceof Player) {
            cloneType = EntityType.ZOMBIE;
        }
        LivingEntity clone = (LivingEntity) diedEntity.getWorld().spawnEntity(diedEntity.getLocation(), cloneType);
        clone.setAI(false);
        clone.setCollidable(false);
        clone.setInvulnerable(true);
        clone.setFireTicks(100);
        if (diedEntity instanceof Ageable) {
            ((Ageable) clone).setAge(((Ageable) diedEntity).getAge());
        }
        for (PotionEffect potionEffect : diedEntity.getActivePotionEffects()) {
            clone.addPotionEffect(potionEffect);
        }
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (clone.getEquipment() != null && diedEntity.getEquipment() != null)
            clone.getEquipment().setItem(slot, diedEntity.getEquipment().getItem(slot), true);
        }
        return clone;
    }
}
