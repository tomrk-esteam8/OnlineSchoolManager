package me.esteam8.osm.repository;

import me.esteam8.osm.model.Salary;

public class SalaryDAO extends BaseDAO<Salary> {

    @Override
    protected String getQuery() {
        return "select salary from Salary salary";
    }

}
