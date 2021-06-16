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

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FountainPlayEffect extends BukkitRunnable {
    private int count = 0;
    private final Location startLocation;
    private final Player player;
    private final ArrayList<Item> items;
    private final Material material;

    public FountainPlayEffect(Location startLocation, Player player, Material material) {
        this.startLocation = startLocation;
        this.player = player;
        this.material = material;
        items = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            if (count < 10) {
                Location location = startLocation.clone().add(new Vector(0, 0.75, 0).rotateAroundY(Math.random()*6.28));
                Item item = player.getWorld().dropItem(location, new ItemStack(material));
                item.setVelocity(item.getVelocity().multiply(1.5));
                List<String> lore = new ArrayList<>();
                lore.add(UUID.randomUUID() + "");
                ItemMeta meta = item.getItemStack().getItemMeta();
                if (meta != null) {
                    meta.setLore(lore);
                    item.getItemStack().setItemMeta(meta);
                }
                item.setPickupDelay(2000);
                items.add(item);
            }
            if (count >= 10) {
                items.get(count-10).remove();
            }
            if (++count == 20) {
                cancel();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            cancel();
        }
    }
}