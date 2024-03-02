package com.osama.chatgptintegration.domain;

public record GPTRequest(String prompt, int tokens, String model) {
}
