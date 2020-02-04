package com.ing.bank.controller;

import com.ing.bank.model.Customer;
import com.ing.bank.forms.CustomerRegistrationForm;
import java.util.Calendar;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ing.bank.service.CustomerService;

/**
 *
 * @author muhammad.ajmal
 */
@Controller
@RequestMapping("/registration")
public class CustomerRegistrationController {

  @Autowired
  private CustomerService userService;

  @ModelAttribute("user")
  public CustomerRegistrationForm CustomerRegistrationForm() {
    return new CustomerRegistrationForm();
  }

  @GetMapping
  public String showRegistrationForm(Model model) {
    Date current = new Date();
    Date start = getTime(8);
    Date end = getTime(17);
    return (current.after(start) && current.before(end)) ? "registration" : "registrationNotAllowed";
  }

  @PostMapping
  public String registerCustomerAccount(@ModelAttribute("user") @Valid CustomerRegistrationForm registrationForm,
          BindingResult result) {

    Customer existing = userService.findByEmail(registrationForm.getEmail());
    if (existing != null) {
      result.rejectValue("email", null, "There is already an account registered with that email");
    }

    if (result.hasErrors()) {
      return "registration";
    }

    userService.save(registrationForm);
    return "redirect:/registration?success";
  }

  private Date getTime(int hour) {
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR, hour);
    calendar.set(Calendar.MINUTE, 00);
    calendar.set(Calendar.SECOND, 00);
    return calendar.getTime();
  }
}
