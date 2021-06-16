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

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Storage {
    private final Kryo kryo;
    private Output output;
    private Input input;
    private final String dataFolder;
    private final String SETTINGS_FILE = "settings.dat";
    private final Map<String, PlayerEffects> playersSettings = new HashMap<>();
    private PlayerSettingsDto playerSettingsDto;

    public Storage(String dataFolder) {
        this.dataFolder = dataFolder;
        kryo = new Kryo();
        kryo.setDefaultSerializer(CompatibleFieldSerializer.class);
        kryo.register(String.class);
        kryo.register(HashMap.class);
        kryo.register(ArrayList.class);
        kryo.register(EffectSettingsDto.class);
        kryo.register(PlayerSettingsDto.class);
        loadData();
    }

    private void loadData() {
        try {
            input = new Input(new FileInputStream(getFilePath(SETTINGS_FILE)));
            playerSettingsDto = kryo.readObject(input, PlayerSettingsDto.class);
        } catch (FileNotFoundException ex) {
            Logger.getLogger("Input Storage file not found.");
        } catch (KryoException ex) {
            ex.printStackTrace();
        }
        if (input != null) {
            input.close();
        }
        if (playerSettingsDto == null) {
            playerSettingsDto = new PlayerSettingsDto();
        }
        if (playerSettingsDto.update()) {
            saveData();
        }
    }

    private void saveData() {
        try {
            output = new Output(new FileOutputStream(getFilePath(SETTINGS_FILE)));
            kryo.writeObject(output, playerSettingsDto);
        } catch (FileNotFoundException ex) {
            Logger.getLogger("Output Storage file not found.");
        }
        output.close();
    }

    public String getFilePath(String fileName) {
        File directory = new File(dataFolder);
        if (! directory.exists()){
            if (!directory.mkdir()) {
                System.out.println("Can't create directory " + dataFolder);
            }
        }
        return Paths.get(dataFolder, fileName).toAbsolutePath().toString();
    }

    @NotNull
    public PlayerEffects getPlayerEffects(String playerId) {
        if (playersSettings.get(playerId) == null) {
            playersSettings.put(playerId, new PlayerEffects(playerSettingsDto.getSettingsForPlayer(playerId)));
        }
        return playersSettings.get(playerId);
    }

    public void saveSettings() {
        saveData();
    }
}