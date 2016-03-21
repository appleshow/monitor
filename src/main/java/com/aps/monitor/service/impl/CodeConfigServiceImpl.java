package com.aps.monitor.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aps.monitor.dao.ComCodeMapper;
import com.aps.monitor.model.ComCode;
import com.aps.monitor.model.ComCodeKey;
import com.aps.monitor.service.ICodeConfigService;

@Service
public class CodeConfigServiceImpl implements ICodeConfigService {
	@Resource
	private ComCodeMapper comCodeMapper;

	@Override
	public int deleteByPrimaryKey(ComCodeKey key) {
		return comCodeMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(ComCode record) {
		return comCodeMapper.insert(record);
	}

	@Override
	public int insertSelective(ComCode record) {
		return comCodeMapper.insertSelective(record);
	}

	@Override
	public ComCode selectByPrimaryKey(ComCodeKey key) {
		return comCodeMapper.selectByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKeySelective(ComCode record) {
		return comCodeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyMap(Map<String, String> recode) {
		return comCodeMapper.updateByPrimaryKeyMap(recode);
	}

	@Override
	public int updateByPrimaryKey(ComCode record) {
		return comCodeMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ComCode> selectByCondition(ComCode record) {
		return comCodeMapper.selectByCondition(record);
	}

}
