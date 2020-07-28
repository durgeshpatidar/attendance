package com.incture.attendance.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.dto.EmployeeListDto;
import com.incture.attendance.dto.ManagerDetailsDto;
import com.incture.attendance.dto.ProfileDto;
import com.incture.attendance.entities.DesignationMasterDo;
import com.incture.attendance.entities.EmployeeDo;
import com.incture.attendance.entities.EmployeeMasterDo;
import com.incture.attendance.entities.ManagerMasterDo;

@SuppressWarnings("rawtypes")
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

	// For saving employee data and adding address from master after signup to
	// address transaction table.
	@Override
	public boolean saveEmployeeData(EmployeeDto employeeDto) {

		Query q = getSession().createQuery("select id from EmployeeDo where id=:id");
		q.setParameter("id", employeeDto.getId());
		try {
			if (!q.list().isEmpty())
				return false;
			getSession().save(importDto(employeeDto));
			addressDao.addMasterAddress(employeeDto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return true;
	}

	// For verifying employee email and password during login.
	@Override
	public boolean verifyIdPass(EmployeeDto employeeDto) {
		String hql = "select id FROM EmployeeDo WHERE email=:email AND password=:password";
		Query q = getSession().createQuery(hql);
		q.setParameter("email", employeeDto.getEmail());
		q.setParameter("password", employeeDto.getPassword());
		try {
			if (q.list().isEmpty())
				return false;
			String id = (String) q.list().get(0);
			employeeDto.setId(id);
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}

		return true;
	}

	// For checking employee email is exist or not and employee is active or not
	@Override
	public boolean isValidUser(EmployeeDto employeeDto) {
		String hql = "select id FROM EmployeeMasterDo WHERE email=:email and status=:status";
		Query q = getSession().createQuery(hql);
		q.setParameter("email", employeeDto.getEmail());
		q.setParameter("status", "ACTIVE");
		String id = (String) q.getSingleResult();
		if (id == null)
			return false;
		employeeDto.setId(id);
		return true;
	}

	// For displaying profile details
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
		try {

			Query q = getSession().createQuery("from DesignationMasterDo where employeeId=:id and status=:status");
			q.setParameter("id", id);
			q.setParameter("status", "ACTIVE");
			DesignationMasterDo designation = (DesignationMasterDo) q.getSingleResult();
			profileDto.setDesignation(designation.getDesignation());
		} catch (Exception e) {
			System.out.println(e);
		}
		return profileDto;

	}

	// For displaying manager details
	@Override
	public List<ManagerDetailsDto> managerDetails(EmployeeDto employeeDto) {

		Query q = getSession().createQuery(
				"FROM ManagerMasterDo where employeeId=:id and status=:status and managerType=:managerType");
		q.setParameter("id", employeeDto.getId());
		q.setParameter("status", "ACTIVE");
		q.setParameter("managerType", "PROJECT");
		ManagerMasterDo result = (ManagerMasterDo) q.getSingleResult();
		Query q1 = getSession().createQuery("FROM EmployeeMasterDo where id=:id");
		q1.setParameter("id", result.getManagerId());
		EmployeeMasterDo man = (EmployeeMasterDo) q1.getSingleResult();
		// System.out.println(results);
		// List<ManagerDetailsDto> managerList = new ArrayList<>();
		// for (ManagerMasterDo b : results) {
		// ManagerDetailsDto manager = new ManagerDetailsDto();
		// EmployeeMasterDo empMasterDo = getSession().get(EmployeeMasterDo.class,
		// b.getManagerId());
		// System.out.println(empMasterDo);
		// manager.setFirstName(empMasterDo.getFirstName());
		// manager.setLastName(empMasterDo.getLastName());
		// manager.setManagerType(b.getManagerType());
		// manager.setEmailId(empMasterDo.getEmailId());
		// managerList.add(manager);
		// }
		List<ManagerDetailsDto> managers = new ArrayList<>();
		ManagerDetailsDto manager = new ManagerDetailsDto();
		manager.setFirstName(man.getFirstName());
		manager.setLastName(man.getLastName());
		manager.setManagerType("PROJECT");
		manager.setEmailId(man.getEmailId());
		managers.add(manager);
		return managers;
	}

	// Verfiying whether email is already of a logged in user in forgot password
	// situation.
	@Override
	public boolean verifyEmail(EmployeeDto employeeDto) {
		String hql = "select id FROM EmployeeDo WHERE email=:email";
		Query query = getSession().createQuery(hql);
		query.setParameter("email", employeeDto.getEmail());
		if (query.list().isEmpty())
			return false;
		return true;
	}

	// Updating password
	@Override
	public void updatePassword(EmployeeDto employeeDto) {
		String hql = "UPDATE EmployeeDo SET password=:password WHERE email=:email";
		Query query = getSession().createQuery(hql);
		query.setParameter("password", employeeDto.getPassword());
		query.setParameter("email", employeeDto.getEmail());
		query.executeUpdate();
	}

	// Checking whether employee is manager or not for giving views accordingly.
	@Override
	public boolean verifyEmployeeType(String empId) {

		String hql = "SELECT managerId FROM ManagerMasterDo where managerId=:id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", empId);
		try {
			if (query.getResultList().isEmpty())
				return false;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		return true;
	}

	// Getting list of employee under manager.
	@Override
	public List<EmployeeListDto> getEmployeeList(String empId) {
		List<EmployeeListDto> employees = new ArrayList<>();
		// Adding details of manager also to the list
		EmployeeListDto employee1 = new EmployeeListDto();
		employee1.setId(empId);
		employee1.setName("You");
		employees.add(employee1);

		Query q2 = getSession().createQuery("SELECT employeeId FROM ManagerMasterDo where managerId=:id");
		q2.setParameter("id", empId);
		@SuppressWarnings("unchecked")
		List<String> employeeIdList = q2.list();

		Query q3 = getSession().createQuery("FROM EmployeeMasterDo where id in(:employeeIdList)");
		q3.setParameterList("employeeIdList", employeeIdList);
		@SuppressWarnings("unchecked")
		List<EmployeeMasterDo> emd = q3.list();
		EmployeeListDto employee = null;
		for (EmployeeMasterDo e : emd) {
			employee = new EmployeeListDto();
			employee.setId(e.getId());
			employee.setName(e.getFirstName() + " " + e.getLastName());
			employees.add(employee);
		}
		return employees;
	}

	// Getting list of all employee for admin.
	@Override
	public Object getAllEmployeeList(String empId) {
		List<EmployeeListDto> employees = new ArrayList<>();
		// Adding admin to list first
		EmployeeListDto admin = new EmployeeListDto();
		admin.setId(empId);
		admin.setName("You");
		employees.add(admin);

		Query q2 = getSession().createQuery("SELECT id FROM EmployeeDo where id!=:id");
		q2.setParameter("id", empId);
		@SuppressWarnings("unchecked")
		List<String> employeeIdList = q2.list();

		Query q3 = getSession().createQuery("FROM EmployeeMasterDo where id in(:employeeIdList)");
		q3.setParameterList("employeeIdList", employeeIdList);
		@SuppressWarnings("unchecked")
		List<EmployeeMasterDo> emd = q3.list();
		EmployeeListDto employee = null;
		for (EmployeeMasterDo e : emd) {
			employee = new EmployeeListDto();
			employee.setId(e.getId());
			employee.setName(e.getFirstName() + " " + e.getLastName());
			employees.add(employee);
		}

		return employees;

	}

	// Checking whether employee is admin or not for providing views accordingly.
	@Override
	public boolean verifyAdminId(String empId) {
		String hql = "FROM EmployeeDo where userType=:userType and id =:id";
		Query query = getSession().createQuery(hql);
		query.setParameter("userType", "ADMIN");
		query.setParameter("id", empId);
		try {
			if (query.getResultList().isEmpty())
				return false;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		return true;
	}

}
