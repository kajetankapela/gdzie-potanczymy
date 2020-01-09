package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.AddressController;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.AddressService;
import com.gdziepotanczymy.service.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AddressViewController {
    private final AddressService addressService;

    @GetMapping("/all-addresses")
    public ModelAndView displayAddressesTable() {
        List<AddressDto> addresses = addressService.getAllAddresses();

        ModelAndView modelAndView = new ModelAndView("addresses_table");
        modelAndView.addObject("addresses", addresses);

        return modelAndView;
    }

    @GetMapping("/delete-address/{id}")
    public String deleteAddress(@PathVariable Long id) throws NotFound {
        ModelAndView modelAndView = new ModelAndView("all-addresses");

        addressService.deleteAddressById(id);

        return "redirect:/all-addresses";
    }
}
