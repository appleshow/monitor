package com.aps.monitor.model;

import java.util.Date;
import java.util.List;

public class ComForm {
	private Integer formId;

	private String formName;

	private Integer formFdsg;

	private Integer formBdsg;

	private String formProc;

	private Integer prflag;

	private String prgroup;

	private String prtype;

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

	private String prinf;

	private String prexp;

	private Date itime;

	private String ishift;

	private String igroup;

	private Integer iperson;

	private Date utime;

	private String ushift;

	private String ugroup;

	private Integer uperson;

	private String ufrom;

	private List<ComFormRights> comFormRights;

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName == null ? null : formName.trim();
	}

	public Integer getFormFdsg() {
		return formFdsg;
	}

	public void setFormFdsg(Integer formFdsg) {
		this.formFdsg = formFdsg;
	}

	public Integer getFormBdsg() {
		return formBdsg;
	}

	public void setFormBdsg(Integer formBdsg) {
		this.formBdsg = formBdsg;
	}

	public String getFormProc() {
		return formProc;
	}

	public void setFormProc(String formProc) {
		this.formProc = formProc == null ? null : formProc.trim();
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

	public Date getItime() {
		return itime;
	}

	public void setItime(Date itime) {
		this.itime = itime;
	}

	public String getIshift() {
		return ishift;
	}

	public void setIshift(String ishift) {
		this.ishift = ishift == null ? null : ishift.trim();
	}

	public String getIgroup() {
		return igroup;
	}

	public void setIgroup(String igroup) {
		this.igroup = igroup == null ? null : igroup.trim();
	}

	public Integer getIperson() {
		return iperson;
	}

	public void setIperson(Integer iperson) {
		this.iperson = iperson;
	}

	public Date getUtime() {
		return utime;
	}

	public void setUtime(Date utime) {
		this.utime = utime;
	}

	public String getUshift() {
		return ushift;
	}

	public void setUshift(String ushift) {
		this.ushift = ushift == null ? null : ushift.trim();
	}

	public String getUgroup() {
		return ugroup;
	}

	public void setUgroup(String ugroup) {
		this.ugroup = ugroup == null ? null : ugroup.trim();
	}

	public Integer getUperson() {
		return uperson;
	}

	public void setUperson(Integer uperson) {
		this.uperson = uperson;
	}

	public String getUfrom() {
		return ufrom;
	}

	public void setUfrom(String ufrom) {
		this.ufrom = ufrom == null ? null : ufrom.trim();
	}

	public List<ComFormRights> getComFormRights() {
		return comFormRights;
	}

	public void setComFormRights(List<ComFormRights> comFormRights) {
		this.comFormRights = comFormRights;
	}

}