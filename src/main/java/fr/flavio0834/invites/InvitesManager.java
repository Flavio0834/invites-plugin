package fr.flavio0834.invites;

import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvitesManager {

    private final JavaPlugin plugin;
    private final File invitesFile;
    private final JSONObject invitesData;

    public InvitesManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.invitesFile = new File(plugin.getDataFolder(), "invites.json");
        this.invitesData = new JSONObject();

        // Charge les données depuis le fichier au démarrage du plugin
        loadInvites();
    }

    public void loadInvites() {
        if (!invitesFile.exists()) {
            try {
                invitesFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (FileReader reader = new FileReader(invitesFile)) {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(reader);
                if (obj instanceof JSONObject) {
                    invitesData.putAll((JSONObject) obj);
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveInvites() {
        try (FileWriter writer = new FileWriter(invitesFile)) {
            writer.write(invitesData.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addInvite(String player, String inviter) {
        invitesData.put(player, inviter);
        saveInvites();
    }

    public String getInviter(String player) {
        return (String) invitesData.get(player);
    }

    public Map<String, String> getAllInvites() {
        Map<String, String> invitesMap = new HashMap<>();
        for (Object key : invitesData.keySet()) {
            String player = (String) key;
            String inviter = (String) invitesData.get(key);
            invitesMap.put(player, inviter);
        }
        return invitesMap;
    }

    public boolean isPlayerRegistered(String player) {
        return this.getAllInvites().keySet().contains(player);
    }

    public List<String> getInvitedPlayers(String inviter) {
        Map<String, String> invites = this.getAllInvites();
        List<String> invitedPlayers = new ArrayList<String>();

        for (Map.Entry<String, String> entry : invites.entrySet()) {
            if ((entry.getValue() != null) && (entry.getValue().equals(inviter))) {
                invitedPlayers.add(entry.getKey());
            }
        }

        return invitedPlayers;
    }
}
