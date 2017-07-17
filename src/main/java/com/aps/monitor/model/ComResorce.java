package com.aps.monitor.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ComResorce {
	private Integer resourceId;

	private String resourceTypeA;

	private String resourceTypeB;

	private String resourceTypeC;

	private String resourceTypeD;

	private Integer resourceTypeE;

	private Integer resourceTypeF;

	private Integer resourceTypeG;

	private Integer resourceTypeH;

	private Date resourceTypeK;

	private Date resourceTypeL;

	private Date resourceTypeM;

	private Integer prflag;

	private String prgroup;

	private String prtype;

	private String prclass;

	private String property0;

	private String property1;

	private String property2;

	private String property3;

	private String property4;

	private String property5;

	private String property6;

	private String property7;

	private String property8;

	private String property9;

	private BigDecimal property10;

	private BigDecimal property11;

	private BigDecimal property12;

	private BigDecimal property13;

	private BigDecimal property14;

	private BigDecimal property15;

	private String prinf;

	private String prexp;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date itime;

	private Integer iperson;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date utime;

	private Integer uperson;

	private String dflag;

	private Date dtime;

	private Integer dperson;

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceTypeA() {
		return resourceTypeA;
	}

	public void setResourceTypeA(String resourceTypeA) {
		this.resourceTypeA = resourceTypeA == null ? null : resourceTypeA.trim();
	}

	public String getResourceTypeB() {
		return resourceTypeB;
	}

	public void setResourceTypeB(String resourceTypeB) {
		this.resourceTypeB = resourceTypeB == null ? null : resourceTypeB.trim();
	}

	public String getResourceTypeC() {
		return resourceTypeC;
	}

	public void setResourceTypeC(String resourceTypeC) {
		this.resourceTypeC = resourceTypeC == null ? null : resourceTypeC.trim();
	}

	public String getResourceTypeD() {
		return resourceTypeD;
	}

	public void setResourceTypeD(String resourceTypeD) {
		this.resourceTypeD = resourceTypeD == null ? null : resourceTypeD.trim();
	}

	public Integer getResourceTypeE() {
		return resourceTypeE;
	}

	public void setResourceTypeE(Integer resourceTypeE) {
		this.resourceTypeE = resourceTypeE;
	}

	public Integer getResourceTypeF() {
		return resourceTypeF;
	}

	public void setResourceTypeF(Integer resourceTypeF) {
		this.resourceTypeF = resourceTypeF;
	}

	public Integer getResourceTypeG() {
		return resourceTypeG;
	}

	public void setResourceTypeG(Integer resourceTypeG) {
		this.resourceTypeG = resourceTypeG;
	}

	public Integer getResourceTypeH() {
		return resourceTypeH;
	}

	public void setResourceTypeH(Integer resourceTypeH) {
		this.resourceTypeH = resourceTypeH;
	}

	public Date getResourceTypeK() {
		return resourceTypeK;
	}

	public void setResourceTypeK(Date resourceTypeK) {
		this.resourceTypeK = resourceTypeK;
	}

	public Date getResourceTypeL() {
		return resourceTypeL;
	}

	public void setResourceTypeL(Date resourceTypeL) {
		this.resourceTypeL = resourceTypeL;
	}

	public Date getResourceTypeM() {
		return resourceTypeM;
	}

	public void setResourceTypeM(Date resourceTypeM) {
		this.resourceTypeM = resourceTypeM;
	}

	public Integer getPrflag() {
		return prflag;
	}

	public void setPrflag(Integer prflag) {
		this.prflag = prflag;
	}

	public String getPrgroup() {
		return prgroup;
	}

	public void setPrgroup(String prgroup) {
		this.prgroup = prgroup == null ? null : prgroup.trim();
	}

	public String getPrtype() {
		return prtype;
	}

	public void setPrtype(String prtype) {
		this.prtype = prtype == null ? null : prtype.trim();
	}

	public String getPrclass() {
		return prclass;
	}

	public void setPrclass(String prclass) {
		this.prclass = prclass == null ? null : prclass.trim();
	}

	public String getProperty0() {
		return property0;
	}

	public void setProperty0(String property0) {
		this.property0 = property0 == null ? null : property0.trim();
	}

	public String getProperty1() {
		return property1;
	}

	public void setProperty1(String property1) {
		this.property1 = property1 == null ? null : property1.trim();
	}

	public String getProperty2() {
		return property2;
	}

	public void setProperty2(String property2) {
		this.property2 = property2 == null ? null : property2.trim();
	}

	public String getProperty3() {
		return property3;
	}

	public void setProperty3(String property3) {
		this.property3 = property3 == null ? null : property3.trim();
	}

	public String getProperty4() {
		return property4;
	}

	public void setProperty4(String property4) {
		this.property4 = property4 == null ? null : property4.trim();
	}

	public String getProperty5() {
		return property5;
	}

	public void setProperty5(String property5) {
		this.property5 = property5 == null ? null : property5.trim();
	}

	public String getProperty6() {
		return property6;
	}

	public void setProperty6(String property6) {
		this.property6 = property6 == null ? null : property6.trim();
	}

	public String getProperty7() {
		return property7;
	}

	public void setProperty7(String property7) {
		this.property7 = property7 == null ? null : property7.trim();
	}

	public String getProperty8() {
		return property8;
	}

	public void setProperty8(String property8) {
		this.property8 = property8 == null ? null : property8.trim();
	}

	public String getProperty9() {
		return property9;
	}

	public void setProperty9(String property9) {
		this.property9 = property9 == null ? null : property9.trim();
	}

	public BigDecimal getProperty10() {
		return property10;
	}

	public void setProperty10(BigDecimal property10) {
		this.property10 = property10;
	}

	public BigDecimal getProperty11() {
		return property11;
	}

	public void setProperty11(BigDecimal property11) {
		this.property11 = property11;
	}

	public BigDecimal getProperty12() {
		return property12;
	}

	public void setProperty12(BigDecimal property12) {
		this.property12 = property12;
	}

	public BigDecimal getProperty13() {
		return property13;
	}

	public void setProperty13(BigDecimal property13) {
		this.property13 = property13;
	}

	public BigDecimal getProperty14() {
		return property14;
	}

	public void setProperty14(BigDecimal property14) {
		this.property14 = property14;
	}

	public BigDecimal getProperty15() {
		return property15;
	}

	public void setProperty15(BigDecimal property15) {
		this.property15 = property15;
	}

	public String getPrinf() {
		return prinf;
	}

	public void setPrinf(String prinf) {
		this.prinf = prinf == null ? null : prinf.trim();
	}

	public String getPrexp() {
		return prexp;
	}

	public void setPrexp(String prexp) {
		this.prexp = prexp == null ? null : prexp.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getItime() {
		return itime;
	}

	public void setItime(Date itime) {
		this.itime = itime;
	}

	public Integer getIperson() {
		return iperson;
	}

	public void setIperson(Integer iperson) {
		this.iperson = iperson;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getUtime() {
		return utime;
	}

	public void setUtime(Date utime) {
		this.utime = utime;
	}

	public Integer getUperson() {
		return uperson;
	}

	public void setUperson(Integer uperson) {
		this.uperson = uperson;
	}

	public String getDflag() {
		return dflag;
	}

	public void setDflag(String dflag) {
		this.dflag = dflag == null ? null : dflag.trim();
	}

	public Date getDtime() {
		return dtime;
	}

	public void setDtime(Date dtime) {
		this.dtime = dtime;
	}

	public Integer getDperson() {
		return dperson;
	}

	public void setDperson(Integer dperson) {
		this.dperson = dperson;
	}
}