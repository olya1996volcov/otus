package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.rest.dto.CommentDto;
import ru.otus.spring.service.crud.CommentCrudService;
import ru.otus.spring.util.DtoDomainMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentCrudService commentService;

    @PostMapping(value = "/api/book/{id}/comment")
    public void createNewComment(@PathVariable("id") long id, @RequestBody CommentDto dto) {
        commentService.saveByBookId(dto, id);
    }

    @GetMapping(value = "/api/book/{id}/comment")
    public List<CommentDto> getAllCommentsByBookId(@PathVariable("id") long id) {
        return commentService.showAllCommentsByBookId(id).stream().map(
                comment -> DtoDomainMapper.toDto(comment.getId(), comment.getText(), comment.getBook().getId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/comment/{id}")
    public CommentDto geCommentById(@PathVariable("id") long id) {
        Comment comment = commentService.showCommentById(id).orElseThrow(NotFoundException::new);

        return DtoDomainMapper.toDto(comment);
    }

    @DeleteMapping("/api/comment/{id}")
    public void deleteCommentById(@PathVariable("id") long id) {
        commentService.deleteCommentById(id);
    }

    @PutMapping("/api/comment/{id}/text")
    public CommentDto updateBookTitleById(@PathVariable("id") long id, @RequestParam("text") String text) {
        return DtoDomainMapper.toDto(commentService.updateCommentTextById(id, text));
    }
}
