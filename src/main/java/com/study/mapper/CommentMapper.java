package com.study.mapper;

import com.study.domain.CommentDTO;
import com.study.domain.CommentDeleteRequest;
import com.study.domain.CommentRequest;
import com.study.domain.CommentSaveRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

	int saveComment(CommentSaveRequest commentSaveRequest);

	CommentDTO selectCommentDetail(Long idx);

	int updateComment(CommentDTO params);

	void deleteComment(CommentDeleteRequest commentDeleteRequest);

	List<CommentDTO> selectCommentList(CommentRequest commentRequest);

	int selectCommentTotalCount(Long id);

}
