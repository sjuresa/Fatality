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

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import nomblox.fatality.effect.FatalityEffect;
import nomblox.fatality.effect.text.Text;
import org.bukkit.entity.Player;

import java.util.List;

public class TextCategory extends Category {
    public static final String NAME = "Text";

    public TextCategory(List<EffectSettingsDto> settings) {
        super(settings, NAME);
    }

    public void apply(Player player, String entity) {
        FatalityEffect fatalityEffect = getRandomActiveEffect(player, entity);
        if (fatalityEffect != null) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(((Text)fatalityEffect).getText()));
        }
    }
}