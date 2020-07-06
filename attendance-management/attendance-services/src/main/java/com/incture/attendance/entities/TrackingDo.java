package com.incture.attendance.entities;


@Entity
@Table(name = "TRACKING")
class TrackingDo implements BaseDo
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
	
	@Column(name = "CHECKIN", columnDefinition = "TIMESTAMP")
	private Time checkin;
	
	@Column(name = "CHECKOUT", columnDefinition = "TIMESTAMP")
	private Time checkout;
	
}