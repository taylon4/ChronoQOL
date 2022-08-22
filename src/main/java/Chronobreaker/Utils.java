package Chronobreaker;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Vec3;
import scala.actors.threadpool.Arrays;

//Useful things
public class Utils {
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static boolean nullCheck() {
		if(mc.theWorld != null && mc.thePlayer != null) return true;
		return false;
	}
	
	public static Vec3 getPlayerPos() {
		return mc.thePlayer.getPositionVector();
	}

}
