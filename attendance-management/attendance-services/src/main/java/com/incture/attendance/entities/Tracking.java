package com.incture.attendance.entities;


@Entity
@Table(name = "TRACKING")
class Tracking implements BaseDo
{
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ID")
	private String id;
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ID")
	private EmployeeDo employee;
	
	@ManyToOne
	@JoinColumn(name="ADDRESS_ID")
	private EmployeeDo address;
	
	@Column(name = "DATE", columnDefinition = "DATE")
	private Date date;
	
	
}