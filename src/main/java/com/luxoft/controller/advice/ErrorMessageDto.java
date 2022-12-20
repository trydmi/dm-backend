package com.luxoft.controller.advice;

public record ErrorMessageDto(int httpStatusCode, String message) {
}
