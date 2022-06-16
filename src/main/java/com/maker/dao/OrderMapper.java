package com.maker.dao;

import java.util.List;

import com.maker.domain.Order;

public interface OrderMapper {
	public List<Order> findAll()throws Exception;
}
