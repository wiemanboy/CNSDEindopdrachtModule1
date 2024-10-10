package com.wiemanboy.board.presentation;

import com.wiemanboy.board.domain.exceptions.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${debug:false}")
    private boolean debug;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        if (debug) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred");
    }

    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<String> handleBoardNotFoundException(BoardNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Board with id %s not found", exception.getBoardId()));
    }

    @ExceptionHandler(InvalidHexCodeException.class)
    public ResponseEntity<String> handleInvalidHexCodeException(InvalidHexCodeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(String.format("Invalid hex code: %s", exception.getHexCode()));
    }

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<String> handleTagNotFoundException(TagNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Tag with id %s not found", exception.getTagId()));
    }

    @ExceptionHandler(TaskListNotFoundException.class)
    public ResponseEntity<String> handleTaskListNotFoundException(TaskListNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Task list with id %s not found", exception.getTaskListId()));
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Task with id %s not found", exception.getTaskId()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("User with id %s not found", exception.getUserId()));
    }
}