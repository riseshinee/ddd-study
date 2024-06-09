/**
 * 서비스 계층에서 요구하는 입력을 제공하고 결과를 호출자에게 반환하는 것까지만 책임
 */
@Controller
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/create")
  public ModelAndView create(ContactDetails contactDetails) {
    OperationResult result = userService.create(contactDetails);
    return new ModelAndView("resultView", "result", result);
  }
}
