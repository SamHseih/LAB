package ccu.pllab.tcgen.CBTCGUtility;

import java.util.ArrayList;
import java.util.HashMap;

public class RetString {
	/*
	 * �^�Ǧh�Ӧr��ϥ� HashMap key���^�Ǫ��r��W�Avalue�h�s�r�ꤺ�e
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
