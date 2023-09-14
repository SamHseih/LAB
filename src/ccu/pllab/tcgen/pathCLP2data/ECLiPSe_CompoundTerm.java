package ccu.pllab.tcgen.pathCLP2data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.parctechnologies.eclipse.Atom;
import com.parctechnologies.eclipse.CompoundTerm;
import com.parctechnologies.eclipse.CompoundTermImpl;

import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;

public class ECLiPSe_CompoundTerm {
	private List<Object> terms;
	private Integer intValue;
	private String stringValue;
	private Object functorValue;

	public ECLiPSe_CompoundTerm() {
		terms = new LinkedList<Object>();
	}

	public ECLiPSe_CompoundTerm(Object compundTerm, int i) {
		terms = new LinkedList<Object>();
		if (compundTerm instanceof LinkedList) {
			if (((List) compundTerm).size() > 0) {
				LinkedList<Object> input_terms = (LinkedList<Object>) compundTerm;
				for (Object o : input_terms) {
					terms.add(analysisCompoundTerm(o));
				}
			}
		}
	}

	// 202107新增陣列測試資料格式，並整理至此函式
	private Object analysisCompoundTerm(Object o) {
		if (o instanceof LinkedList) {// 20210325 dai 新增
			return new ECLiPSe_CompoundTerm(o, 1).getTerm();
		} else if (o instanceof String) {
			o = ((String) o).replace("\"", "\\\"");
			 o = ((String) o).replaceAll("\\p{C}", "?");
			return ("\"" + o + "\"");
		} else if (o == null) {
			return "[]";
		} else if (o instanceof CompoundTerm) {
			if (o instanceof CompoundTermImpl) {// array type test data
				Object objList = "";
				for (int i = 1; i <= ((CompoundTermImpl) o).arity(); i++) {
					objList = objList.toString() + this.analysisCompoundTerm(((CompoundTermImpl) o).arg(i)).toString()
							+ ", ";
				}
				objList = Utility.delEndRedundantSymbol(objList.toString(), ", ");
				return ((CompoundTermImpl) o).functor() + "(" + objList + ")";
			} else if (o instanceof Atom) {
				switch (((Atom) o).functor()) {
				case "true":// boolean
				case "false":
					return ((Atom) o).functor().toString();
				default:
					return ((Atom) o).toString();
				}
			} else
				return o.toString();
		} else {
			return o;
		}
	}

	public ECLiPSe_CompoundTerm(Object compundTerm) {
		if (compundTerm instanceof LinkedList) {
			terms = new LinkedList<Object>();
			@SuppressWarnings("unchecked")
			LinkedList<Object> input_terms = (LinkedList<Object>) compundTerm;
			for (Object o : input_terms) {
				terms.add(new ECLiPSe_CompoundTerm(o));
			}
		} else if (compundTerm instanceof Integer) {
			intValue = (Integer) compundTerm;
		} else if (compundTerm instanceof String) {

			stringValue = "\"" + (String) compundTerm + "\"";
		} else {
			functorValue = compundTerm;
		}

	}

	@Override
	public String toString() {
		if (terms != null) {
//			if (terms.size() > 0
//					&& (terms.get(0).toString().equals("uml_obj") || terms.get(0).toString().equals("uml_asc"))) {
//				return terms.toString().replaceFirst("\\[", "(").substring(0, terms.toString().length() - 1) + ")";
//			} else {

			return terms.toString();
//			}
		} else if (intValue != null) {
			return intValue.toString();
		} else if (stringValue != null) {
			return stringValue;
		} else if (functorValue != null) {
			return ((CompoundTerm) functorValue).functor();
		} else {
			return "[]";
		}
	}

	public Integer getIntValue() {
		return this.intValue;
	}

	public String getStringValue() {
		return this.stringValue;
	}

	public List<Object> getTerm() {
		return this.terms;
	}
}
