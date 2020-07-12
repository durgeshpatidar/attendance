package com.incture.attendance.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.incture.attendance.dto.AddressDto;
import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.dto.ManagerDetailsDto;
import com.incture.attendance.dto.ProfileDto;
import com.incture.attendance.entities.AddressMasterDo;
import com.incture.attendance.entities.EmployeeDo;
import com.incture.attendance.entities.EmployeeMasterDo;
import com.incture.attendance.entities.ManagerMasterDo;
import com.incture.attendance.entities.OfficeAddressDo;


@Repository("EmployeeDaoImpl")
public class EmployeeDaoImpl extends BaseDao<EmployeeDo, EmployeeDto> implements EmployeeDao {
	@Autowired
	AddressDaoImpl addressDao;
	@Override
	protected EmployeeDo importDto(EmployeeDto employeeDto) {
		EmployeeDo entity = null;
		if (employeeDto != null) {
			entity = new EmployeeDo();
			entity.setId(employeeDto.getId());
			entity.setPhoneNo(employeeDto.getPhoneNo());
			entity.setPassword(employeeDto.getPassword());
			entity.setEmail(employeeDto.getEmail());
		}
		return entity;
	}

	@Override
	protected EmployeeDto exportDto(EmployeeDo employeeDo) {
		EmployeeDto dto = null;
		if (employeeDo != null) {
			dto = new EmployeeDto();
			dto.setId(employeeDo.getId());
			dto.setPhoneNo(employeeDo.getPhoneNo());
			dto.setPassword(employeeDo.getPassword());
			dto.setEmail(employeeDo.getEmail());
		}
		return dto;
	}
	//for save employee data and adding address from master after signup
	@Override
	public void saveEmployeeData(EmployeeDto employeeDto) {

		getSession().save(importDto(employeeDto));
		String empId=employeeDto.getId();
		List<AddressDto> address = new ArrayList<>();
		//Taking homeaddress from address master
		@SuppressWarnings("deprecation")
		Criteria crit1 = getSession().createCriteria(AddressMasterDo.class);
		crit1.add(Restrictions.eq("empId",empId));
		AddressMasterDo addMasterDo = (AddressMasterDo)crit1.uniqueResult();
		AddressDto homeAddress = new AddressDto();
		//adding home address to an addressDto.
		homeAddress.setEmpId(empId);
		homeAddress.setAddress(addMasterDo.getAddress());
		homeAddress.setCity(addMasterDo.getCity());
		homeAddress.setState(addMasterDo.getState());
		homeAddress.setPincode(addMasterDo.getPincode());
		homeAddress.setLocationLat(addMasterDo.getLocationLat());
		homeAddress.setLocationLon(addMasterDo.getLocationLat());
		address.add(homeAddress);
		//Taking static company address from master
		@SuppressWarnings("deprecation")
		Criteria crit2 = getSession().createCriteria(OfficeAddressDo.class);
		@SuppressWarnings("unchecked")
		List<OfficeAddressDo> officeAddresses = crit2.list();
		for(OfficeAddressDo value:officeAddresses) {
			AddressDto officeAddress = new AddressDto();
			officeAddress.setEmpId(empId);
			officeAddress.setAddress(value.getAddress());
			officeAddress.setState(value.getState());
			officeAddress.setCity(value.getCity());
			officeAddress.setPincode(value.getPincode());
			officeAddress.setLocationLat(value.getLocationLat());
			officeAddress.setLocationLon(value.getLocationLon());
			address.add(officeAddress);
			
		}
		//Adding master addresses to address transaction table.
		for(AddressDto add: address) {
			addressDao.addAddress(add);
		}
	
		

	}

	//for verifying employee email and password
	@Override
	public boolean verifyIdPass(EmployeeDto employeeDto) {
		
		Query q=getSession().createNativeQuery("SELECT ID FROM EMPLOYEE WHERE EMAIL='"+employeeDto.getEmail()+"' AND PASSWORD='"+employeeDto.getPassword()+"';");
		@SuppressWarnings("unchecked")
		List<Object> l=(List<Object>)q.getResultList();
		int size=l.size();
		if(size!=0)
			return true;
		else
			return false;
	}
	//for checking employee email is exist or not and employee is active or not
	@Override
	public boolean isValidUser(EmployeeDto employeeDto)
	{
		Query q=getSession().createNativeQuery("SELECT ID FROM EMPLOYEE_MASTER WHERE EMAIL='"+employeeDto.getEmail()+"' AND STATUS='ACTIVE';");
		System.out.println("email of employee:"+employeeDto.getEmail());
		@SuppressWarnings("unchecked")
		List<Object> l=(List<Object>)q.getResultList();
		employeeDto.setId((String)l.get(0));
		int size=l.size();
		System.out.println("size : "+size);
		if(size!=0)
			return true;
		else
			return false;	
	}
//for displaying profile details
	@Override
	public ProfileDto profileDetails(EmployeeDto employeeDto) {
		String id = employeeDto.getId();
		ProfileDto profileDto = new ProfileDto();
		EmployeeMasterDo empMasterDto = getSession().get(EmployeeMasterDo.class, id);
		profileDto.setId(empMasterDto.getId());
		profileDto.setFirstName(empMasterDto.getFirstName());
		profileDto.setLastName(empMasterDto.getLastName());
		profileDto.setGender(empMasterDto.getGender());
		profileDto.setBloodGroup(empMasterDto.getBloodGroup());
		profileDto.setEmailId(empMasterDto.getEmailId());
		profileDto.setProfileImg(empMasterDto.getProfileImg());
		return profileDto;

	}
//For displaying manager details
	@Override
	public List<ManagerDetailsDto> managerDetails(EmployeeDto employeeDto) {
		String id = employeeDto.getId();
		@SuppressWarnings("deprecation")
		Date d = new Date(9999,12,12);
		@SuppressWarnings("deprecation")
		Criteria crit = getSession().createCriteria(ManagerMasterDo.class);
        crit.add(Restrictions.eq("employeeId",id));
        crit.add(Restrictions.eq("endDate", d));
        @SuppressWarnings("unchecked")
		List<ManagerMasterDo> results = crit.list();
        System.out.println(results);
        List<ManagerDetailsDto> managerList = new ArrayList<>();
        for(ManagerMasterDo b: results) {
        	ManagerDetailsDto manager = new ManagerDetailsDto();
        	EmployeeMasterDo empMasterDo = getSession().get(EmployeeMasterDo.class, b.getManagerId());
        	System.out.println(empMasterDo);
        	manager.setFirstName(empMasterDo.getFirstName());
        	manager.setLastName(empMasterDo.getLastName());
        	manager.setManagerType(b.getManagerType());
        	managerList.add(manager);
        	
        	
        }
        
		return managerList;
	}

	
}
