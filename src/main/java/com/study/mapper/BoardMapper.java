package com.study.mapper;


import com.study.domain.BoardCreateDTO;
import com.study.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    /**
     * 게시판 리스트 조회
     * @return 게시판 리스트
     */
    List<BoardDTO> findAll();

    /**
     * 게시판 상세정보 조회
     * @param id - PK
     * @return 게시판 상세정보
     */
    BoardDTO findById(Long id);

    void saveBoard(BoardCreateDTO boardCreateDTO);
}
