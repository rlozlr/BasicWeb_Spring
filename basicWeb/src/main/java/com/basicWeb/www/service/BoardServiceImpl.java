package com.basicWeb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basicWeb.www.domain.BoardDTO;
import com.basicWeb.www.domain.BoardVO;
import com.basicWeb.www.domain.FileVO;
import com.basicWeb.www.domain.PagingVO;
import com.basicWeb.www.repository.BoardDAO;
import com.basicWeb.www.repository.CommentDAO;
import com.basicWeb.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

	private final BoardDAO bdao;
	private final CommentDAO cdao;
	private final FileDAO fdao;

	@Transactional
	@Override
	public int register(BoardDTO bdto) {
		// TODO Auto-generated method stub
		int isOk = bdao.register(bdto.getBvo());
		
		if(bdto.getFlist() == null) {
			return isOk;
		}
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			long bno = bdao.selectOneBno();
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isOk += fdao.insertFile(fvo);
			}
		}
		return isOk;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		bdao.getCmtCount();
		return bdao.getList(pgvo);
	}

	@Transactional
	@Override
	public BoardDTO getDetail(long bno) {
		bdao.upReadCount(bno, 1);
		BoardVO bvo = bdao.getDetail(bno);
		List<FileVO> flist = fdao.getFileList(bno);
		BoardDTO bdto = new BoardDTO(bvo, flist);
		return bdto;
	}
	
	@Transactional
	@Override
	public int modify(BoardDTO bdto) {
		bdao.upReadCount(bdto.getBvo().getBno(), -2);
		int isOk = bdao.update(bdto.getBvo());
		if(bdto.getFlist() == null) {
			return isOk;
		}
		
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bdto.getBvo().getBno());
				isOk += fdao.insertFile(fvo);
			}
		}
		return isOk;
	}

	@Override
	public void remove(BoardVO bvo) {
		cdao.deleteAll(bvo);
		fdao.deleteAll(bvo);
		bdao.delete(bvo);	
	}

	@Override
	public int totalCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.totalCount(pgvo);
	}

	@Override
	public int removeFile(String uuid) {
		// TODO Auto-generated method stub
		return fdao.deleteFile(uuid);
	}
	
}
