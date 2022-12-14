package Chronobreaker;

import java.util.ArrayList;

public class HistoryLog<T> extends ArrayList<Object[]>{
	
	private long lastTime = 0;
	//how often a log should be put in, higher = less frequent logs
	private long step = 30;
	
	public HistoryLog() {
		super();
	}
	
	public boolean add(Object[] log) {
		
		if((long)log[0] - lastTime < step) {
			return false;
		}
		lastTime = (long)log[0];
		super.add(log);
		
		return true;
	}
	
}
