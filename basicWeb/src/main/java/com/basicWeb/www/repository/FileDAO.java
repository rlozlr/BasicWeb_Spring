package com.basicWeb.www.repository;

import java.util.List;

import com.basicWeb.www.domain.BoardVO;
import com.basicWeb.www.domain.FileVO;

public interface FileDAO {

	int insertFile(FileVO fvo);

	List<FileVO> getFileList(long bno);

	int deleteFile(String uuid);

	void deleteAll(BoardVO bvo);

}
