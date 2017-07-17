package com.aps.monitor.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ComCode extends ComCodeKey {

	private String codeName;

	private String codeName1;

	private String codeName2;

	private String codeName3;

	private String codeName4;

	private String codeName5;

	private String codeDesc;

	private String codeDesc1;

	private String codeDesc2;

	private String codeDesc3;

	private String codeDesc4;

	private String codeDesc5;

	private Integer codeIndex;

	private Integer codeSeq;

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

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName == null ? null : codeName.trim();
	}

	public String getCodeName1() {
		return codeName1;
	}

	public void setCodeName1(String codeName1) {
		this.codeName1 = codeName1 == null ? null : codeName1.trim();
	}

	public String getCodeName2() {
		return codeName2;
	}

	public void setCodeName2(String codeName2) {
		this.codeName2 = codeName2 == null ? null : codeName2.trim();
	}

	public String getCodeName3() {
		return codeName3;
	}

	public void setCodeName3(String codeName3) {
		this.codeName3 = codeName3 == null ? null : codeName3.trim();
	}

	public String getCodeName4() {
		return codeName4;
	}

	public void setCodeName4(String codeName4) {
		this.codeName4 = codeName4 == null ? null : codeName4.trim();
	}

	public String getCodeName5() {
		return codeName5;
	}

	public void setCodeName5(String codeName5) {
		this.codeName5 = codeName5 == null ? null : codeName5.trim();
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc == null ? null : codeDesc.trim();
	}

	public String getCodeDesc1() {
		return codeDesc1;
	}

	public void setCodeDesc1(String codeDesc1) {
		this.codeDesc1 = codeDesc1 == null ? null : codeDesc1.trim();
	}

	public String getCodeDesc2() {
		return codeDesc2;
	}

	public void setCodeDesc2(String codeDesc2) {
		this.codeDesc2 = codeDesc2 == null ? null : codeDesc2.trim();
	}

	public String getCodeDesc3() {
		return codeDesc3;
	}

	public void setCodeDesc3(String codeDesc3) {
		this.codeDesc3 = codeDesc3 == null ? null : codeDesc3.trim();
	}

	public String getCodeDesc4() {
		return codeDesc4;
	}

	public void setCodeDesc4(String codeDesc4) {
		this.codeDesc4 = codeDesc4 == null ? null : codeDesc4.trim();
	}

	public String getCodeDesc5() {
		return codeDesc5;
	}

	public void setCodeDesc5(String codeDesc5) {
		this.codeDesc5 = codeDesc5 == null ? null : codeDesc5.trim();
	}

	public Integer getCodeIndex() {
		return codeIndex;
	}

	public void setCodeIndex(Integer codeIndex) {
		this.codeIndex = codeIndex;
	}

	public Integer getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Integer codeSeq) {
		this.codeSeq = codeSeq;
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
}