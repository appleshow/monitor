package com.aps.monitor.service;

import java.util.List;

import com.aps.monitor.model.ComCode;
import com.aps.monitor.model.ComForm;
import com.aps.monitor.model.ComFormRight;
import com.aps.monitor.model.ComFormRightKey;

public interface IFormConfigService {

	List<ComForm> selectFormByCondition(ComForm record);

	List<ComFormRight> selectFormRightByCondition(ComFormRight record);

	int insertFormSelective(ComForm record);

	int insertFormRightSelective(ComFormRight record);

	int updateFormBySelective(ComForm record);

	int updateFormRightBySelective(ComFormRight record);

	int deleteFormByPrimaryKey(Integer formId);

	int deleteFormRightByPrimaryKey(ComFormRightKey key);

	List<ComCode> selectFormCtlType(List<String> codeType);
}
