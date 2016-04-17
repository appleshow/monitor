package com.aps.monitor.comm;

import java.util.Map;

public class RequestRefPar {

	private int parCount;
	private Map<String, String> inPar;

	/**
	 * 
	 * @Title: hasPar
	 * @Description: TODO
	 * @param: @return
	 * @return: boolean
	 * @throws
	 * @since 1.0.0
	 */
	public boolean hasPar() {
		return parCount > 0 ? true : false;
	}

	/**
	 * 
	 * @Title: getStringPar
	 * @Description: TODO
	 * @param: @param name
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	public String getStringPar(String name) {
		if (hasPar()) {
			if (inPar.containsKey(name)) {
				return inPar.get(name);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @Title: getIntegerPar
	 * @Description: TODO
	 * @param: @param name
	 * @param: @return
	 * @return: Integer
	 * @throws
	 * @since 1.0.0
	 */
	public Integer getIntegerPar(String name) {
		if (hasPar()) {
			if (inPar.containsKey(name)) {
				try {
					int par = Integer.parseInt(inPar.get(name));

					return par;
				} catch (Exception e) {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @Title: getFloatPar
	 * @Description: TODO
	 * @param: @param name
	 * @param: @return
	 * @return: Float
	 * @throws
	 * @since 1.0.0
	 */
	public Float getFloatPar(String name) {
		if (hasPar()) {
			if (inPar.containsKey(name)) {
				try {
					float par = Float.parseFloat(inPar.get(name));

					return par;
				} catch (Exception e) {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @Title: getDoublePar
	 * @Description: TODO
	 * @param: @param name
	 * @param: @return
	 * @return: Double
	 * @throws
	 * @since 1.0.0
	 */
	public Double getDoublePar(String name) {
		if (hasPar()) {
			if (inPar.containsKey(name)) {
				try {
					double par = Double.parseDouble(inPar.get(name));

					return par;
				} catch (Exception e) {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * @Title: getParCount
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */

	public int getParCount() {
		return parCount;
	}

	/**
	 * @Title: setParCount
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */
	public void setParCount(int parCount) {
		this.parCount = parCount;
	}

	/**
	 * @Title: getInPar
	 * @Description:
	 * @return: Map<String,String>
	 * @since 1.0.0
	 */

	public Map<String, String> getInPar() {
		return inPar;
	}

	/**
	 * @Title: setInPar
	 * @Description:
	 * @return: Map<String,String>
	 * @since 1.0.0
	 */
	public void setInPar(Map<String, String> inPar) {
		this.inPar = inPar;
	}

}
