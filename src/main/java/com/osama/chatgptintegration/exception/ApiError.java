package com.osama.chatgptintegration.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

public record ApiError(String path, String message, HttpStatus status, Date date) {
}
