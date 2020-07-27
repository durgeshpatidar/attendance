package com.incture.attendance.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.incture.attendance.dto.AddressDto;
import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.dto.WorkflowTaskDto;
import com.incture.attendance.entities.AddressDo;
import com.incture.attendance.entities.AddressMasterDo;
import com.incture.attendance.entities.EmployeeDo;
import com.incture.attendance.entities.OfficeAddressDo;

@Repository("AddressDaoImpl")
public class AddressDaoImpl extends BaseDao<AddressDo, AddressDto> implements AddressDao {
	@Autowired
	private WorkflowTaskDaoImpl wtd;

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

	// Adding address and adding address request to workflow transaction table.
	@Override
	public void addAddress(AddressDto addressdto) {
		// Adding address to transaction table.
		addressdto.setStatus("Pending");
		AddressDo newAdd = importDto(addressdto);
		getSession().save(newAdd);

		// Adding workflow for the newly added address
		WorkflowTaskDto wtdo = new WorkflowTaskDto();
		String description = "Address : " + addressdto.getAddress() + " " + addressdto.getCity() + " "
				+ addressdto.getState() + " " + addressdto.getPincode();
		wtdo.setDescription(description);
		wtdo.setEmpId(addressdto.getEmpId());
		Date dt = new Date();
		wtdo.setRequestDate(dt);
		wtdo.setId(newAdd.getId());
		wtdo.setStatus("Pending");
		wtdo.setQuerytype("Change in location");
		wtd.addWorkflowTask(wtdo);
	}

	// Getting all address details.
	@Override
	public List<AddressDto> getAddressDetails(String empId) {

		String hql = "from AddressDo where employee =:employee";
		Query query = getSession().createQuery(hql);
		query.setParameter("employee", getSession().get(EmployeeDo.class, empId));
		@SuppressWarnings("unchecked")
		List<AddressDo> address = query.getResultList();
		List<AddressDto> request = new ArrayList<>();
		AddressDto newAddress = null;
		for (AddressDo t : address) {
			newAddress = new AddressDto();
			newAddress.setId(t.getId());
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

	// Verifying address during checkin.
	@Override
	public String validateAddress(AddressDto addressDto) {

		double lan1 = addressDto.getLocationLat() - 2;
		double lan2 = addressDto.getLocationLat() + 2;
		double lon1 = addressDto.getLocationLon() - 2;
		double lon2 = addressDto.getLocationLon() + 2;
		String hql = "from AddressDo where employee =:employee and status =:status and locationLat>=:lan1 and locationLat<=:lan2 and"
				+ "locationLon>=:lon1 and locationLon<=:lon2";
		Query query = getSession().createQuery(hql);
		query.setParameter("employee", getSession().get(EmployeeDo.class, addressDto.getEmpId()));
		query.setParameter("status", "Approved");
		query.setParameter("lan1", lan1);
		query.setParameter("lan2", lan2);
		query.setParameter("lon1",lon1);
		query.setParameter("lon2",lon2);
		try {
			AddressDo address = (AddressDo) query.getSingleResult();
			return address.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	// Method for adding master address during signup.
	@Override
	public void addMasterAddress(EmployeeDto employeeDto) {
		String empId = employeeDto.getId();
		List<AddressDto> address = new ArrayList<>();
		// Taking homeaddress from address master
		String hql = "from AddressMasterDo where empId =:empId";
		Query query = getSession().createQuery(hql);
		query.setParameter("empId", empId);
		AddressMasterDo addMasterDo = (AddressMasterDo) query.getSingleResult();
		AddressDto homeAddress = new AddressDto();
		// adding home address to an addressDto.
		homeAddress.setEmpId(empId);
		homeAddress.setAddress(addMasterDo.getAddress());
		homeAddress.setCity(addMasterDo.getCity());
		homeAddress.setState(addMasterDo.getState());
		homeAddress.setPincode(addMasterDo.getPincode());
		homeAddress.setLocationLat(addMasterDo.getLocationLat());
		homeAddress.setLocationLon(addMasterDo.getLocationLat());
		homeAddress.setStatus("ACTIVE");
		address.add(homeAddress);
		// Taking static company address from master
		String hql2 = "from OfficeAddressDo";
		Query query2 = getSession().createQuery(hql2);
		@SuppressWarnings("unchecked")
		List<OfficeAddressDo> officeAddresses = query2.getResultList();
		AddressDto officeAddress = new AddressDto();
		for (OfficeAddressDo value : officeAddresses) {

			officeAddress.setEmpId(empId);
			officeAddress.setAddress(value.getAddress());
			officeAddress.setState(value.getState());
			officeAddress.setCity(value.getCity());
			officeAddress.setPincode(value.getPincode());
			officeAddress.setLocationLat(value.getLocationLat());
			officeAddress.setLocationLon(value.getLocationLon());
			officeAddress.setStatus("ACTIVE");
			address.add(officeAddress);

		}

		// Adding master addresses to address transaction table.
		for (AddressDto add : address) {
			getSession().save(importDto(add));

		}

	}

}
