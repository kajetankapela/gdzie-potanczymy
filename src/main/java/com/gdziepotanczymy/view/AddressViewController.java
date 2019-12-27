package com.gdziepotanczymy.view;

import com.gdziepotanczymy.service.AddressService;
import com.gdziepotanczymy.service.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
