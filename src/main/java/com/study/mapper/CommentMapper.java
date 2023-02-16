package com.study.mapper;

import com.study.domain.CommentDTO;
import com.study.domain.CommentRequest;
import com.study.domain.CommentSaveRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

	public int saveComment(CommentSaveRequest commentSaveRequest);

	public CommentDTO selectCommentDetail(Long idx);

	public int updateComment(CommentDTO params);

	public int deleteComment(Long idx);

	public List<CommentDTO> selectCommentList(CommentRequest commentRequest);

	public int selectCommentTotalCount(Long id);

}
