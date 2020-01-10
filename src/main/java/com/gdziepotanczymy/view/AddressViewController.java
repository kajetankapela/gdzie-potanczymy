package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.AddressController;
import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.AddressService;
import com.gdziepotanczymy.service.dto.AddressDto;
import com.gdziepotanczymy.service.dto.CreateUpdateAddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/new-address")
    public ModelAndView displayCreateAddressForm() {
        CreateUpdateAddressDto createUpdateAddressDto = new CreateUpdateAddressDto();

        ModelAndView modelAndView = new ModelAndView("create_address_form");
        modelAndView.addObject("createUpdateAddressDto", createUpdateAddressDto);

        return modelAndView;
    }

    @PostMapping("/new-address")
    public String createAddress(@ModelAttribute CreateUpdateAddressDto createUpdateAddressDto) throws BadRequest {
        addressService.createAddress(createUpdateAddressDto);

        return "redirect:/all-addresses";
    }
}
