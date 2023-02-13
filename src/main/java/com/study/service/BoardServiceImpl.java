package com.study.service;

import com.study.domain.BoardDTO;
import com.study.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    public List<BoardDTO> getBoardList(){
        List<BoardDTO> boardList = boardMapper.findAll();
        return boardList;
    }

    @Override
    public BoardDTO getBoardDetail(Long boardId) {
        return boardMapper.findById(boardId);
    }

}
