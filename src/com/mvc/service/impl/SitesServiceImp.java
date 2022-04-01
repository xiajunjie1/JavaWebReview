package com.mvc.service.impl;

import java.util.List;

import com.mvc.dao.IsitesDao;
import com.mvc.service.IsitesService;
import com.mvc.util.factory.ObjectFactory;
import com.mvc.vo.Sites;

public class SitesServiceImp implements IsitesService {
	private IsitesDao sitedao=ObjectFactory.getDaoInstance("sites.dao", IsitesDao.class);
	@Override
	public List<Sites> getSites() throws Exception {
		// TODO Auto-generated method stub
		List<Sites> list=sitedao.findAll();
		return list;
	}

}
