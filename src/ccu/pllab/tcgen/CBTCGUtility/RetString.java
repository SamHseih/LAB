package ccu.pllab.tcgen.CBTCGUtility;

import java.util.ArrayList;
import java.util.HashMap;

public class RetString {
	/*
	 * 回傳多個字串使用 HashMap key為回傳的字串名，value則存字串內容
	 */
	HashMap<String, String> retStr;

	public RetString() {
		retStr = new HashMap<String, String>();
	}

	public void addRetString(String strName, String str) {
		retStr.put(strName, str);
		return;
	}

	public String getRetString(String strName) {
		return retStr.get(strName);
	}

	public ArrayList<String> getAllRetName() {
		ArrayList<String> ret = new ArrayList<String>();
		for (String strName : retStr.keySet()) {
			ret.add(strName);
		}
		return ret;
	}

}
