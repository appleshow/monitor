package com.aps.monitor.service;

import java.util.List;

import com.aps.monitor.model.ComCode;
import com.aps.monitor.model.ComForm;
import com.aps.monitor.model.ComOrg;
import com.aps.monitor.model.ComOrgForm;
import com.aps.monitor.model.ComOrgFormKey;

public interface IOrgFormConfigService {
	List<ComCode> selectCombData(List<String> codeType);

	List<ComOrg> selectByCondition(ComOrg record);

	List<ComForm> selectByCondition(ComForm record);

	int deleteByPrimaryKey(ComOrgFormKey key);

	int insertSelective(ComOrgForm record);

	List<ComOrgForm> selectByCondition(ComOrgForm record);

	int updateByPrimaryKeySelective(ComOrgForm record);

}
