package com.basicWeb.www.service;

import com.basicWeb.www.domain.CommentVO;
import com.basicWeb.www.domain.PagingVO;
import com.basicWeb.www.handler.PagingHandler;

public interface CommentService{

	int post(CommentVO cvo);

	PagingHandler getList(long bno, PagingVO pgvo);

}
