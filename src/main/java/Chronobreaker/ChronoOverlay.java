package Chronobreaker;

import java.awt.Color;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChronoOverlay{
	public static boolean showcd = true;
	public static Cooldown cooldown = new Cooldown(0);
	
	public static void startCooldown(int duration) {
		if(cooldown.isDone()) {
			cooldown = new Cooldown(duration);
			cooldown.start();
		}
		
	}
	
	@SubscribeEvent
	public void renderHotBar(RenderGameOverlayEvent event) {
		if(!ChronoTrack.enabled || !showcd || event.isCancelable() || event.type != ElementType.HOTBAR) {
			return;
		}
		
		//really these could be labeled as left and top
		int width = (event.resolution.getScaledWidth()- 176)/2;
		int height = (event.resolution.getScaledHeight()) - 19;
		
		//iterate through slot id 36-44 (hotbar slots)
		for(int i = 36; i < 45; i++) {
			Slot slot = Utils.mc.thePlayer.inventoryContainer.getSlot(i);
			if(slot.getHasStack() && slot.getStack().getDisplayName().contains("Echo")) {
				
				width += 20 * (i-36);
				
				if(!cooldown.isDone()) {
					renderTextInSlot(width, height);
					renderSquareInSlot(width, height, Color.red.getRGB());
				}
				else {
					renderSquareInSlot(width, height, Color.GREEN.getRGB());
				}
		        
			}
		}
		
	}
	
	@SubscribeEvent
    public void renderEchoInventory(GuiScreenEvent.DrawScreenEvent.Pre event) {
		if(ChronoTrack.enabled && showcd && event.gui instanceof GuiInventory) {
			GuiInventory guiInv = (GuiInventory)event.gui;
			Container slots = guiInv.inventorySlots;
			for(Slot s : slots.inventorySlots) {
				if(s.getHasStack() && s.getStack().getDisplayName().contains("Echo")) {
					if(cooldown.isDone()) {
						renderSlotInInventory(s, guiInv, Color.green.getRGB());
					}
					else {
						renderSlotInInventory(s, guiInv, 1077293360);
					}
					
				}
				
			}

		}
	}
	
	//renders the cooldown text onto item if it is in the hotbar
	private void renderTextInSlot(int left, int top) {

		GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, 260);
		FontRenderer fontrd = Utils.mc.fontRendererObj;
		
		//Renders text in the center of slot, needs to adjust if there is only 1 digit on the countdown
		if(cooldown.getTimeLeft() < 10) {
			fontrd.drawString(""+cooldown.getTimeLeft(), left + 5.9F, top + 4.5F, Color.black.getRGB(), false);
			fontrd.drawString(""+cooldown.getTimeLeft(), left + 6F, top + 4.5F, Color.red.getRGB(), false);
		}
		else {
			fontrd.drawString(""+cooldown.getTimeLeft(), left + 2.4F, top + 4.5F, Color.black.getRGB(), false);
			fontrd.drawString(""+cooldown.getTimeLeft(), left + 2.5F, top + 4.5F, Color.red.getRGB(), false);
		}
		
		GlStateManager.popMatrix();
	
	}
	
	private void renderSquareInSlot(int slotX, int slotY, int color) {
		//if color is green, render the green square behind the itemStack in the slot
        if(color == Color.green.getRGB()) {
        	GlStateManager.pushMatrix();
            GlStateManager.translate(0, 0, 120);
            Gui.drawRect(slotX, slotY, slotX + 16, slotY + 16, color);
            GlStateManager.popMatrix();
        }
        
        //if the color is gray, render behind the itemStack in the slot
        else {
            GlStateManager.pushMatrix();
        	//by translating where z = 260 we write over the clock instead of behind it
            GlStateManager.enableBlend();
            GlStateManager.translate(0, 0, 120);
            Gui.drawRect(slotX, slotY, slotX + 16, slotY + 16, Color.red.getRGB());
            GlStateManager.popMatrix();
        }
	}
	
	//render the Echo item while in inventory
	private void renderSlotInInventory(Slot slot, GuiInventory guiInv, int color) {
        int guiLeft = (guiInv.width - 176) / 2;
        int inventoryRows = guiInv.inventorySlots.getSlot(0).inventory.getSizeInventory() / 9;
        int ySize = 274 - 108 + inventoryRows * 18;
        int guiTop = (guiInv.height - ySize) / 2;

        int slotX = guiLeft + slot.xDisplayPosition;
        int slotY = guiTop + slot.yDisplayPosition;

        if(!cooldown.isDone()) {
			renderTextInSlot(slotX, slotY);
		}
        
        renderSquareInSlot(slotX, slotY, color);
		
    }
	
	public static class Cooldown extends Thread{
		
		int duration;
		
		public Cooldown(int duration) {
			this.duration = duration-1;
		}
		
		public void run() {
			while(duration > 0) {
				try {
					Thread.sleep(990);
					duration -= 1;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Thread.currentThread().interrupt();
				}
			}
			
		}
		
		public int getTimeLeft() {
			return this.duration;
		}
		
		public boolean isDone() {
			return this.duration <= 0;
		}
		
	}
	
}
