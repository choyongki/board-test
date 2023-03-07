package study.board.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.board.domain.dto.BoardDTO;
import study.board.domain.dto.PostDTO;
import study.board.mapper.BoardMapper;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardMapper boardMapper;

    public List<BoardDTO> getBoardList() {
        return boardMapper.findAll().stream().map(BoardDTO::fromBoardVO).collect(Collectors.toList());
    }

    public BoardDTO getBoard(Long boardId) {
        return BoardDTO.fromBoardVO(boardMapper.findById(boardId));
    }

    public void saveBoard(BoardDTO boardDTO) {
        boardMapper.saveBoard(boardDTO.toBoardVO());
    }


}
