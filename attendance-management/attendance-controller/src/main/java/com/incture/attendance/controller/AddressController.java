
package com.incture.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incture.attendance.dto.AddressDto;
import com.incture.attendance.service.AddressService;
import com.incture.attendance.utils.ResponseDto;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping
	public String TestApi() {
		return "Done";
	}

	@PostMapping
	public ResponseDto addAddress(@RequestBody AddressDto addressDto) {
		return addressService.addAddress(addressDto);
		
	}

}
