package pl.tomaszrarok.osm.repository;

import pl.tomaszrarok.osm.model.Salary;

public class SalaryDAO extends BaseDAO<Salary> {

    @Override
    protected String getQuery() {
        return "select salary from Salary salary";
    }

}
