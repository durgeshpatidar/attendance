package com.incture.attendance.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
			entity.setStatus(addressDto.getStatus());
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
			dto.setStatus(addressDo.getStatus());
			dto.setEmpId(addressDo.getEmployee().getId());
		}
		return dto;
	}
	
	//

	@Override
	public void addAddress(AddressDto addressdto) {
		getSession().save(importDto(addressdto));
	}
	
	@Override
	public List<AddressDto> getAddressDetails(String empId) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(AddressDo.class);
		criteria.add(Restrictions.eq("employee", getSession().get(EmployeeDo.class, empId)));
		@SuppressWarnings("unchecked")
		List<AddressDo> address = criteria.list();
		List<AddressDto> request = new ArrayList<>();
		for (AddressDo t : address) {
			AddressDto newAddress = new AddressDto();
			newAddress.setEmpId(empId);
			newAddress.setAddress(t.getAddress());
			newAddress.setCity(t.getCity());
			newAddress.setState(t.getState());
			newAddress.setPincode(t.getPincode());
			newAddress.setValidTo(t.getValidTo());
			newAddress.setValidFrom(t.getValidFrom());
			newAddress.setLocationLat(t.getLocationLat());
			newAddress.setStatus(t.getStatus());
			newAddress.setLocationLon(t.getLocationLon());
			request.add(newAddress);
		}
		return request;
	}
}
