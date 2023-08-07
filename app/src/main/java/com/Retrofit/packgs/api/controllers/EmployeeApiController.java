package com.Retrofit.packgs.api.controllers;

import com.Retrofit.packgs.interfaces.ListCalBack;
import com.Retrofit.packgs.interfaces.ProcessCallBack;
import com.Retrofit.packgs.model.Employee;

public class EmployeeApiController {

    public void createEmployee(Employee employee, ProcessCallBack callBack) {
        if (!employee.name.isEmpty() && !employee.mobile.isEmpty() && !employee.nationalNumber.isEmpty() && employee.imagesByteArray != null){
            employee.createEmployee(callBack);
        }else {
            callBack.onFailure("Enter required data!");
        }

    }

    public void getEmployee (ListCalBack<Employee> listCalBack){
        Employee.getEmployee(listCalBack);
    }

    public void delete(Employee employee , ProcessCallBack callback ){
        employee.delete(callback);
    }


    public void update(Employee employee , ProcessCallBack callback){
        if (employee.name != null && employee.mobile != null && employee.nationalNumber != null){
            employee.update(callback);
        }else {
            callback.onFailure("Enter required data!");
        }
    }
}