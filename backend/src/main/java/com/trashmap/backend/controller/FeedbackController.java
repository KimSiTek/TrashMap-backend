package com.trashmap.backend.controller;

import com.trashmap.backend.entity.Feedback;
import com.trashmap.backend.repository.FeedbackRepository;
import com.trashmap.backend.entity.Feedback;
import com.trashmap.backend.repository.FeedbackRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api") // ← 반드시 필요
@CrossOrigin(origins = "*")
public class FeedbackController {

    private final FeedbackRepository feedbackRepository;

    public FeedbackController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @PostMapping("/feedback")
    public ResponseEntity<String> submitFeedback(@RequestBody Map<String, String> body) {
        String message = body.get("message");

        if (message == null || message.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("메시지가 비어 있습니다.");
        }

        Feedback feedback = new Feedback();
        feedback.setMessage(message);
        feedbackRepository.save(feedback);

        return ResponseEntity.ok("피드백이 저장되었습니다.");
    }
}
