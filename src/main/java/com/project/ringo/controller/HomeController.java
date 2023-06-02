package com.project.ringo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ringo.model.dto.PageInfo;

public class HomeController implements Controller{

	@Override
	public Object handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return index(request, response);
	}

	protected PageInfo index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("HomeController Activated:");
		List<String> list = new ArrayList<>();
		
		return new PageInfo("/index.jsp");
	}

}
