package me.esteam8.osm.repository;

import lombok.extern.slf4j.Slf4j;
import me.esteam8.osm.model.Salary;

import java.util.HashMap;
import java.util.TreeMap;

@Slf4j
public class SalariesRepository extends BaseRepository<Salary, SalaryDAO> {

    public SalariesRepository() {
        super(new TreeMap<>(), new SalaryDAO());
    }

    public void saveElementAt(int index) {
        modelDAO.save(getElementAt(index));
    }
}
