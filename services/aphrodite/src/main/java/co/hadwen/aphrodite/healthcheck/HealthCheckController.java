package co.hadwen.aphrodite.healthcheck;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("v1")
@RestController
public class HealthCheckController {
    @GetMapping("/health-check")
    ResponseEntity<String> get() throws Exception {
        return ResponseEntity.ok("ok");
    }
}