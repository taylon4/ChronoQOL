package Chronobreaker;

import java.io.IOException;

import com.google.gson.JsonIOException;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "ChronoQOL", version = "0.9", clientSideOnly = true, acceptedMinecraftVersions = "[1.8.9]")
public class Main {
	

	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
	
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) throws JsonIOException, IOException {

		MinecraftForge.EVENT_BUS.register(new ChronoTrack());
		MinecraftForge.EVENT_BUS.register(new ChronoOverlay());
		ClientCommandHandler.instance.registerCommand(new ChronoCommand());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		
	}
}