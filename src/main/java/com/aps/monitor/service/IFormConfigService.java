package com.aps.monitor.service;

import java.util.List;

import com.aps.monitor.model.ComCode;
import com.aps.monitor.model.ComForm;
import com.aps.monitor.model.ComFormRights;
import com.aps.monitor.model.ComFormRightsKey;

public interface IFormConfigService {

	List<ComForm> selectFormByCondition(ComForm record);

	List<ComFormRights> selectFormRightByCondition(ComFormRights record);

	int insertFormSelective(ComForm record);

	int insertFormRightSelective(ComFormRights record);

	int updateFormBySelective(ComForm record);

	int updateFormRightBySelective(ComFormRights record);

	int deleteFormByPrimaryKey(Integer formId);

	int deleteFormRightByPrimaryKey(ComFormRightsKey key);

	List<ComCode> selectFormCtlType(List<String> codeType);
}
