package de.sodamachinev2500.pistonmuter;

import com.comphenix.protocol.*;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.PacketType;

public final class PistonMuter extends JavaPlugin {

    @Override

    public void onEnable(){

        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.addPacketListener(
                new PacketAdapter(this, ListenerPriority.HIGHEST,
                        PacketType.Play.Server.NAMED_SOUND_EFFECT) {
                    @Override
                    public void onPacketSending(PacketEvent event) {
                        PacketContainer packet = event.getPacket();
                        // Item packets (id: 0x29)
                        if (event.getPacketType() ==
                                PacketType.Play.Server.NAMED_SOUND_EFFECT) {
                            if (packet.getSoundEffects().getValues().contains(Sound.BLOCK_PISTON_CONTRACT )){       //Muting sounds
                                event.setCancelled(true);
                            }

                            if (event.getPacketType() ==
                                    PacketType.Play.Server.NAMED_SOUND_EFFECT) {
                                if (packet.getSoundEffects().getValues().contains(Sound.BLOCK_PISTON_EXTEND)){
                                    event.setCancelled(true);
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
