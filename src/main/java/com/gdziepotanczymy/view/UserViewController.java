package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.UserService;
import com.gdziepotanczymy.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserViewController {
    private final UserService userService;

    @GetMapping("/all-users")
    public ModelAndView displayUsersTable() {
        List<UserDto> users = userService.getAllUsers();

        ModelAndView modelAndView = new ModelAndView("users_table");
        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) throws NotFound {
        ModelAndView modelAndView = new ModelAndView("all-users");

        userService.deleteUserById(id);

        return "redirect:/all-users";
    }
}
