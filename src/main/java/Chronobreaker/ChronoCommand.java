package Chronobreaker;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class ChronoCommand extends CommandBase{

	public String getCommandName() {
		return "chrono";
	}
	
	public String getCommandUsage(ICommandSender sender) {
		return "/chrono";
	}
	public int getRequiredPermissionLevel() {
		return 0;
	}
	
	public void processCommand(ICommandSender sender, String[] args) {
		if(args.length == 0) {
			String header = EnumChatFormatting.DARK_GRAY + "--------- " + EnumChatFormatting.DARK_AQUA + "ChronoQOL " + EnumChatFormatting.GRAY + "v0.9 " + EnumChatFormatting.DARK_GRAY + "---------\n";
			String toggle = EnumChatFormatting.GOLD + "/chrono toggle " + EnumChatFormatting.GRAY + "- Toggles mod on/off\n";
			String teleport = EnumChatFormatting.GOLD + "/chrono tp " + EnumChatFormatting.GRAY + "- Shows the position where you will \n                  " + EnumChatFormatting.GRAY + "teleport to while holding " + EnumChatFormatting.YELLOW + "Echo\n";
			String teleportall = EnumChatFormatting.GOLD + "/chrono tpall " + EnumChatFormatting.GRAY + "- Displays the position you will teleport \n                     " + EnumChatFormatting.GRAY + "to without needing to hold " + EnumChatFormatting.YELLOW + "Echo\n";
			String cooldown = EnumChatFormatting.GOLD + "/chrono cd " + EnumChatFormatting.GRAY + "- Toggles the cooldown indicator\n";
			String footer = EnumChatFormatting.DARK_GRAY+ "------------------------------------------------";
			
			Utils.mc.getRenderViewEntity().addChatMessage((IChatComponent)new ChatComponentText(header + toggle + teleport + teleportall + cooldown));
		}
		
		else switch(args[0].toLowerCase()) {
			case("toggle"):
				ChronoTrack.enabled = !ChronoTrack.enabled;
				break;
			case("tp"):
				ChronoTrack.showTpLoc = !ChronoTrack.showTpLoc;
				break;
			case("tpall"):
				ChronoTrack.showTpAll = !ChronoTrack.showTpAll;
				if(ChronoTrack.showTpLoc == false) {
					ChronoTrack.showTpLoc = true;
				}
				break;
			case("cd"):
				ChronoOverlay.showcd = !ChronoOverlay.showcd;
				break;
		}
		
	}
	
	public void notify(String msg) {
		ChatComponentText alert = new ChatComponentText(EnumChatFormatting.DARK_AQUA + "ChronoQOL" + EnumChatFormatting.DARK_GRAY + EnumChatFormatting.BOLD + " > " + 
														EnumChatFormatting.GRAY + msg);
		Utils.mc.getRenderViewEntity().addChatMessage(alert);
	}
	
}
