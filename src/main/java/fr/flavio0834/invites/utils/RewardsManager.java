package fr.flavio0834.invites.utils;

import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RewardsManager {

    private final File rewardsFile;
    private final JSONObject rewardsData;

    public RewardsManager(JavaPlugin plugin) {
        this.rewardsFile = new File(plugin.getDataFolder(), "rewards.json");
        this.rewardsData = new JSONObject();

        // Charge les données depuis le fichier au démarrage du plugin
        loadRewards();
    }

    public void loadRewards() {
        if (!rewardsFile.exists()) {
            try {
                rewardsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (FileReader reader = new FileReader(rewardsFile)) {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(reader);
                if (obj instanceof JSONObject) {
                    rewardsData.putAll((JSONObject) obj);
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveRewards() {
        try (FileWriter writer = new FileWriter(rewardsFile)) {
            writer.write(rewardsData.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addReward(String number, String group) {
        rewardsData.put(number, group);
        saveRewards();
    }

    public String getGroup(String number) {
        return (String) rewardsData.get(number);
    }

    public Map<String, String> getAllGroups() {
        Map<String, String> rewardsMap = new HashMap<>();
        for (Object key : rewardsData.keySet()) {
            String player = (String) key;
            String inviter = (String) rewardsData.get(key);
            rewardsMap.put(player, inviter);
        }
        return rewardsMap;
    }

    public boolean isRewardDefined(String number) {
        return this.getAllGroups().keySet().contains(number);
    }
}
