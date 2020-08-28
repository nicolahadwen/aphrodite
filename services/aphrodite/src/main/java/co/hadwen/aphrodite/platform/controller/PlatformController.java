package co.hadwen.aphrodite.platform.controller;

import co.hadwen.aphrodite.platform.dto.GetPlatformResponse;
import co.hadwen.aphrodite.platform.store.PlatformStore;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RequestMapping("v1")
@RestController
public class PlatformController {
    private final PlatformStore platformStore;

    @GetMapping("/platforms")
    ResponseEntity<GetPlatformResponse> search(@RequestParam(name = "host") String host) throws Exception {
        System.out.print("Here, found stuff");
        return platformStore.getByHost(host)
                .map(platformEntity -> new GetPlatformResponse(platformEntity.getPlatformId()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}