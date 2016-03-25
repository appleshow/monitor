package com.aps.monitor.service;

import java.util.List;

import com.aps.monitor.model.ComOrg;
import com.aps.monitor.model.ComPerson;
import com.aps.monitor.model.ComPersonOrg;
import com.aps.monitor.model.ComPersonOrgKey;

public interface IPersonOrgConfigService {
	List<ComPerson> selectComPersonByCondition(ComPerson record);

	List<ComOrg> selectComOrgByCondition(ComOrg record);

	List<ComPersonOrg> selectByCondition(ComPerson record);

	int insertSelective(ComPersonOrg record);

	int updateByPrimaryKeySelective(ComPersonOrg record);

	int deleteByPrimaryKey(ComPersonOrgKey key);
}
