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

```
@Component
public class FeignClientInterceptor implements RequestInterceptor {

  private static final String AUTHORIZATION_HEADER="Authorization";
  private static final String TOKEN_TYPE = "Bearer";

  @Override
  public void apply(RequestTemplate requestTemplate) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
      OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
      requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, details.getTokenValue()));
    }
  }
}
```
