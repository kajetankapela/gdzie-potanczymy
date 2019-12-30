package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.AddressController;
import com.gdziepotanczymy.service.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AddressViewController {
    private final AddressController addressController;

    @GetMapping("/all-addresses")
    public ModelAndView displayAddressesTable() {
        List<AddressDto> addresses = addressController.getAllAddresses();

        ModelAndView modelAndView = new ModelAndView("addresses_table");
        modelAndView.addObject("addresses", addresses);

        return modelAndView;
    }
}
