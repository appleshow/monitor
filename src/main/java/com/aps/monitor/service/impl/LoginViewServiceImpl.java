package com.aps.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComOrgFormRightsMapper;
import com.aps.monitor.dao.ComPersonMapper;
import com.aps.monitor.model.ComOrgFormRights;
import com.aps.monitor.model.ComPerson;
import com.aps.monitor.service.ILoginViewService;

/**
 * 
 * @ClassName: LoginViewServiceImpl
 * @Description:TODO
 * @author: AppleShow
 * @date: 2016年3月31日 下午11:00:19
 * 
 * @since 1.0.0
 */
@Service
public class LoginViewServiceImpl implements ILoginViewService {

	@Resource
	private ComPersonMapper comPersonMapper;
	@Resource
	private ComOrgFormRightsMapper comOrgFormRightsMapper;

	/**
	 * 
	 * <p>
	 * Title: selectByPrimaryKey
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param personId
	 * @return
	 * @see com.aps.monitor.service.ILoginViewService#selectByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public ComPerson selectByPrimaryKey(Integer personId) {
		return this.comPersonMapper.selectByPrimaryKey(personId);
	}

	/**
	 * 
	 * <p>
	 * Title: selectByUserName
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param userName
	 * @return
	 * @see com.aps.monitor.service.ILoginViewService#selectByUserName(java.lang.String)
	 */
	@Override
	public ComPerson selectByUserName(String userName) {
		return this.comPersonMapper.selectByUserName(userName);
	}

	/**
	 * 
	 * <p>
	 * Title: selectPersonPermissions
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param personId
	 * @return
	 * @see com.aps.monitor.service.ILoginViewService#selectPersonPermissions(java.lang.Integer)
	 */
	@Override
	public List<ComOrgFormRights> selectPersonPermissions(Integer personId) {
		return comOrgFormRightsMapper.selectByPersonId(personId);
	}

}
