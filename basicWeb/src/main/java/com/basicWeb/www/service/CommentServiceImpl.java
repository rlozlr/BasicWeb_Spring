package com.basicWeb.www.service;

import org.springframework.stereotype.Service;

import com.basicWeb.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	private CommentDAO cdao;
}
