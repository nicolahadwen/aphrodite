package co.hadwen.aphrodite.user;

import co.hadwen.aphrodite.user.dto.CreateUserRequest;
import co.hadwen.aphrodite.user.dto.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import system.user.store.CreateUserParam;
import system.user.store.UserEntity;
import system.user.store.UserStore;

import java.net.URI;

@AllArgsConstructor
@RequestMapping("v1")
@RestController
public class UserController {
    UserStore userStore;
    private static String CREATED_USER_URL = "users/%s";

    @GetMapping("/users/{id}")
    ResponseEntity<User> get(@PathVariable String id) throws Exception {
        return userStore.get(id)
                .map(user -> ResponseEntity.ok(
                        new User(
                                user.getUserId(),
                                user.getUsername(),
                                user.getEmail()
                        )
                )).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/users")
    ResponseEntity<String> create(@RequestBody CreateUserRequest createUserRequest) throws Exception {
        UserEntity user = userStore.create(new CreateUserParam(
                createUserRequest.getEmail(),
                createUserRequest.getPassword(),
                createUserRequest.getUsername()
        ));
        return ResponseEntity.created(new URI(String.format(CREATED_USER_URL, user.getUserId()))).build();
    }
}
