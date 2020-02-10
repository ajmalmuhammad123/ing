package com.ing.bank.controller;

import com.ing.bank.forms.AccountForm;
import com.ing.bank.model.Customer;
import com.ing.bank.forms.CustomerForm;
import com.ing.bank.service.AccountService;
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
import com.ing.bank.view.CustomerView;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author muhammad.ajmal
 */
@Controller
@RequestMapping("/registration")
public class CustomerController {

    @Value("${savings.account.reg.start.time}")
    private Integer startHour;

    @Value("${savings.account.reg.end.time}")
    private Integer endHour;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @ModelAttribute("customer")
    public CustomerForm CustomerRegistrationForm() {
        return new CustomerForm();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        Date current = new Date();
        Date start = getTime(startHour);
        Date end = getTime(endHour);
        return (current.after(start) && current.before(end)) ? "registration" : "registrationNotAllowed";
    }

    @PostMapping
    public String registerCustomerAccount(@ModelAttribute("customer") @Valid CustomerForm registrationForm,
            BindingResult result) throws ParseException {

        Customer existing = customerService.findByEmail(registrationForm.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        CustomerView customerView = customerService.save(registrationForm);
        AccountForm accountForm = buildAccountForm(customerView);
        customerView.getAccounts().add(accountService.save(accountForm));
        return "redirect:/registration?success";
    }

    private Date getTime(int hour) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        return calendar.getTime();
    }

    private AccountForm buildAccountForm(CustomerView customer) {
        AccountForm accountForm = new AccountForm();
        accountForm.setCustomerId(customer.getCustomerId());
        return accountForm;
    }

}
