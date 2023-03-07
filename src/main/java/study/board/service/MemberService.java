package study.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.board.mapper.MemberMapper;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberMapper memberMapper;
}
