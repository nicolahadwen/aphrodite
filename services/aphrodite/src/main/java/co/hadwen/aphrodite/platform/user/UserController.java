package co.hadwen.aphrodite.platform.user;

import co.hadwen.aphrodite.platform.entity.PlatformEntity;
import co.hadwen.aphrodite.platform.store.PlatformStore;
import co.hadwen.aphrodite.platform.user.entity.UserEntity;
import co.hadwen.aphrodite.platform.user.store.UserStore;
import co.hadwen.aphrodite.platform.user.dto.CreateUserRequest;
import co.hadwen.aphrodite.platform.user.dto.GetUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@AllArgsConstructor
@RequestMapping("v1")
@RestController
public class UserController {
    private final PlatformStore platformStore;
    private final UserStore userStore;
    private static String CREATED_USER_URL = "users/%s";

    @GetMapping("/platforms/{platformId}/users/{userId}")
    ResponseEntity<GetUserResponse> get(
            @PathVariable(name = "platformId") String platformId,
            @PathVariable(name = "userId") String userId) throws Exception {
        Optional<PlatformEntity> platformEntity = platformStore.get(platformId);
        if (!platformEntity.isPresent()) {

            return ResponseEntity.notFound().build();
        }
        return userStore.get(userId, platformEntity.get())
                .map(user -> ResponseEntity.ok(
                        new GetUserResponse(
                                user.getUserId()
                        )
                )).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/platforms/{platformId}/users")
    ResponseEntity<String> create(
            @PathVariable(name = "platformId") String platformId,
            @RequestBody CreateUserRequest createUserRequest) throws Exception {
        Optional<PlatformEntity> platform = platformStore.get(platformId);
        if (!platform.isPresent()) {
            return ResponseEntity.badRequest().body("No platform exists");
        }
        UserEntity user = userStore.create(platform.get(), createUserRequest.getUserId());
        return ResponseEntity.created(new URI(String.format(CREATED_USER_URL, user.getUserId()))).build();
    }
}
