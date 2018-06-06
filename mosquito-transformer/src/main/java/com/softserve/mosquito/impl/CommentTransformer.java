package com.softserve.mosquito.impl;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.CommentCreateDto;
import com.softserve.mosquito.entities.Comment;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CommentTransformer {

    private CommentTransformer() {
        throw new IllegalStateException("Utility class");
    }

    public static class CommentCreate implements Transformer<Comment, CommentCreateDto> {

        @Override
        public Comment toEntity(CommentCreateDto commentCreateDto) {
            throw new NotImplementedException();
            /*return new Comment(
                    commentCreateDto.getText(),
                    commentCreateDto.getTaskId(),
                    commentCreateDto.getAuthorId());*/
        }

        @Override
        public CommentCreateDto toDTO(Comment comment) {
            throw new NotImplementedException();
            /* return new CommentCreateDto(
                    comment.getText(),
                    comment.getTaskId(),
                    comment.getAuthorId());*/
            }
        }

}