package Chronobreaker;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;


public class ChronoTrack{
	
	public static boolean enabled = false;
	public static boolean showTpLoc = true;
	public static boolean showTpAll = true;
	
	public static HistoryLog<Object[]> history = new HistoryLog<Object[]>();
	AxisAlignedBB lastBB = null;
	public static boolean inGame = false;
	boolean holdingEcho = false;
	
	public static void stupid(String msg) {
		Utils.mc.thePlayer.addChatMessage(new ChatComponentText(msg));
	}
	
	@SubscribeEvent
	public void trackLoc(PlayerTickEvent event) {
		if(Utils.nullCheck() && inGame) {
			Entity e = Utils.mc.thePlayer;
			long time = System.currentTimeMillis();
			AxisAlignedBB bb = e.getEntityBoundingBox();
					
			Object[] array = new Object[] {time, bb};
			history.add(array);
		}
	}
	//this is a mess
	@SubscribeEvent
	public void renderHolo(RenderWorldLastEvent event) {
		if(Utils.nullCheck()) {
			long now = System.currentTimeMillis();
			

			if(history.size() > 0 && now-10000 > (long)history.get(0)[0]) {
				AxisAlignedBB bb = (AxisAlignedBB)history.remove(0)[1];
				
				if(enabled && showTpLoc && (holdingEcho || (showTpAll && ChronoOverlay.cooldown.isDone()))) {
					ChronoRender.bbRender(bb);
					lastBB = bb;
				}
			}
			//continue to render last rendered box after it is removed from the list
			else if(enabled && lastBB != null && showTpLoc && (holdingEcho || (showTpAll && ChronoOverlay.cooldown.isDone()))) {
				ChronoRender.bbRender(lastBB);
			}
			
			

		}
		
	}
	
	@SubscribeEvent
	public void checkChat(ClientChatReceivedEvent e) {
		//check for start
		String msg = e.message.getFormattedText();
		if(msg.contains("Cages opened!")) {
			inGame = true;
			ChronoOverlay.startCooldown(10);
		}
		
		//check for Echo ability use
		else if(msg.contains("You used your") && msg.contains("Echo")) {
			ChronoOverlay.startCooldown(40);
		}

	}
	
	@SubscribeEvent
	public void getHeldItem(PlayerTickEvent e) {
		if(Utils.nullCheck()) {
			ItemStack item = Utils.mc.thePlayer.getHeldItem();
			if(item != null && item.getDisplayName().contains("Echo")) {
				holdingEcho = true;
			}
			else {
				holdingEcho = false;
			}
		}
		
	}
	
	@SubscribeEvent
	public void resetOnLoad(WorldEvent.Load e) {
		clearHistory();
		ChronoOverlay.startCooldown(0);
		lastBB = null;
		inGame = false;
	}
	
	public static void clearHistory() {
		history.clear();
	}
}
