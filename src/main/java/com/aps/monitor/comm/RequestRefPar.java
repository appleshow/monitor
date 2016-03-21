package com.aps.monitor.comm;

import java.util.Map;

public class RequestRefPar {

	private int parcnt;
	private Map<String, String> inpar;

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
		return parcnt > 0 ? true : false;
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
			if (inpar.containsKey(name)) {
				return inpar.get(name);
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
			if (inpar.containsKey(name)) {
				try {
					int par = Integer.parseInt(inpar.get(name));

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
			if (inpar.containsKey(name)) {
				try {
					float par = Float.parseFloat(inpar.get(name));

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
			if (inpar.containsKey(name)) {
				try {
					double par = Double.parseDouble(inpar.get(name));

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
	 * @Title: getParcnt
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */

	public int getParcnt() {
		return parcnt;
	}

	/**
	 * @Title: setParcnt
	 * @Description:
	 * @return: int
	 * @since 1.0.0
	 */
	public void setParcnt(int parcnt) {
		this.parcnt = parcnt;
	}

	/**
	 * @Title: getInpar
	 * @Description:
	 * @return: Map<String,String>
	 * @since 1.0.0
	 */

	public Map<String, String> getInpar() {
		return inpar;
	}

	/**
	 * @Title: setInpar
	 * @Description:
	 * @return: Map<String,String>
	 * @since 1.0.0
	 */
	public void setInpar(Map<String, String> inpar) {
		this.inpar = inpar;
	}

}
