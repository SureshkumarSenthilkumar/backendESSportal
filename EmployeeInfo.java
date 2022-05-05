package com.te.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfo {
	private int employee_ID;
	private String leaveDate;
	private String leaveStatus;
	@Override
	public String toString() {
		return "EmployeeInfo [employee_ID=" + employee_ID + ", leaveDate=" + leaveDate + ", leaveStatus=" + leaveStatus
				+ "]";
	}
}
