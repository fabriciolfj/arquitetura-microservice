# arquitetura-microservice

###### Exemplo openfeign token

```
@FeignClient(name = "userClient", url ="${userService.hostname}")
public interface MyFeignClient {
String AUTH_TOKEN = “Authorization”;
@GetMapping(“/users”)
@Headers(“Content-Type: application/json”)
List<User> findUsers(@RequestHeader(AUTH_TOKEN) String bearerToken);
}
```
