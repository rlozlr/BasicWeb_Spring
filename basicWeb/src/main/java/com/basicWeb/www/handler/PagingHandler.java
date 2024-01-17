package com.basicWeb.www.handler;

import com.basicWeb.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PagingHandler {
	
	private int startPage;
	private int endPage;
	private int totalCount;
	
	private PagingVO pgvo;
	private Boolean prev, next;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		this.endPage = (int) Math.ceil(pgvo.getPageNo() / (double) pgvo.getQty()) * pgvo.getQty();
		this.startPage = endPage - 9;
		
		int realEndPage = (int)(Math.ceil(totalCount / (double) pgvo.getQty()));
		if(realEndPage < endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = startPage > 1;
		this.next = this.endPage < realEndPage;
	}
	
}
