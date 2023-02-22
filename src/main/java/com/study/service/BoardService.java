package com.study.service;

import com.study.domain.BoardCreateDTO;
import com.study.domain.BoardDTO;

import java.util.List;

public interface BoardService {

    public List<BoardDTO> getBoardList();

    public BoardDTO getBoardDetail(Long boardId);


    void saveBoard(BoardCreateDTO boardCreateDTO);
}

