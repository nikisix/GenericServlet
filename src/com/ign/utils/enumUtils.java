package com.ign.utils;

import java.util.Enumeration;

public class enumUtils {
	private boolean enumContains(Enumeration e, String s){
		while(e.hasMoreElements()){
			if(s.equalsIgnoreCase(e.nextElement().toString()))
				return true;
		}
		return false;
	}
	
	private boolean enumContainsArray(Enumeration e, String [] ss){
		for(String s : ss){
			if(!enumContains(e,s))
				return false;
		}
		return true;
	}
}
