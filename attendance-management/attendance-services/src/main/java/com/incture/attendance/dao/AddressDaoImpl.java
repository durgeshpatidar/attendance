package com.incture.attendance.dao;

import org.springframework.stereotype.Repository;

import com.incture.attendance.dto.AddressDto;
import com.incture.attendance.entities.AddressDo;
import com.incture.attendance.entities.EmployeeDo;

@Repository("AddressDaoImpl")
public class AddressDaoImpl extends BaseDao<AddressDo, AddressDto> implements AddressDao {
	@Override
	protected AddressDo importDto(AddressDto addressDto) {
		AddressDo entity = null;
		if (addressDto != null) {
			entity = new AddressDo();
			entity.setId(addressDto.getId());
			entity.setAddress(addressDto.getAddress());
			entity.setCity(addressDto.getCity());
			entity.setLocationLon(addressDto.getLocationLon());
			entity.setLocationLat(addressDto.getLocationLat());
			entity.setPincode(addressDto.getPincode());
			entity.setState(addressDto.getState());
			entity.setValidFrom(addressDto.getValidFrom());
			entity.setValidTo(addressDto.getValidTo());
			entity.setEmployee(getSession().get(EmployeeDo.class, addressDto.getEmpId()));
		}
		return entity;
	}

	@Override
	protected AddressDto exportDto(AddressDo addressDo) {
		AddressDto dto = null;
		if (addressDo != null) {
			dto = new AddressDto();
			dto.setId(addressDo.getId());
			dto.setAddress(addressDo.getAddress());
			dto.setCity(addressDo.getCity());
			dto.setState(addressDo.getState());
			dto.setLocationLat(addressDo.getLocationLat());
			dto.setLocationLon(addressDo.getLocationLon());
			dto.setPincode(addressDo.getPincode());
			dto.setValidFrom(addressDo.getValidFrom());
			dto.setValidTo(addressDo.getValidTo());
			dto.setEmpId(addressDo.getEmployee().getId());
		}
		return dto;
	}
	//

	@Override
	public void addAddress(AddressDto addressdto) {
		getSession().save(importDto(addressdto));
	}

}
