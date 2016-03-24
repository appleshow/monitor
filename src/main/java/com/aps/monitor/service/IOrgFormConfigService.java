package com.aps.monitor.service;

import java.util.List;

import com.aps.monitor.model.ComCode;
import com.aps.monitor.model.ComForm;
import com.aps.monitor.model.ComFormRights;
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.model.ComOrgForm;
import com.aps.monitor.model.ComOrgFormKey;
import com.aps.monitor.model.ComOrgFormRights;
import com.aps.monitor.model.ComOrgFormRightsKey;

public interface IOrgFormConfigService {
	List<ComCode> selectComCode(ComCode record);

	List<ComOrg> selectComOrg(ComOrg record);

	List<ComForm> selectComForm(ComForm record);

	List<ComOrgForm> selectComOrgForm(ComOrgForm record);

	List<ComFormRights> selectComFormRights(ComFormRights record);

	int deleteByPrimaryKey(ComOrgFormRightsKey key);

	int insertSelective(ComOrgFormRights record);

	List<ComOrgFormRights> selectByCondition(ComOrgFormRights record);

	int updateByPrimaryKeySelective(ComOrgFormRights record);

	int insertComOrgFormSelective(ComOrgForm record);

	int deleteComOrgFormByPrimaryKey(ComOrgFormKey record);
}
