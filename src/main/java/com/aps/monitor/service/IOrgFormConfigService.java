package com.aps.monitor.service;

import java.util.List;

import com.aps.monitor.model.ComCode;
import com.aps.monitor.model.ComForm;
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.model.ComOrgFormRights;
import com.aps.monitor.model.ComOrgFormRightsKey;

public interface IOrgFormConfigService {
	List<ComCode> selectCombData(List<String> codeType);

	List<ComOrg> selectByCondition(ComOrg record);

	List<ComForm> selectByCondition(ComForm record);

	int deleteByPrimaryKey(ComOrgFormRightsKey key);

	int insertSelective(ComOrgFormRights record);

	List<ComOrgFormRights> selectByCondition(ComOrgFormRights record);

	int updateByPrimaryKeySelective(ComOrgFormRights record);

}
