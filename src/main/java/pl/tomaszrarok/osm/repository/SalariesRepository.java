package pl.tomaszrarok.osm.repository;

import lombok.extern.slf4j.Slf4j;
import pl.tomaszrarok.osm.model.Salary;

import java.util.HashMap;
@Slf4j
public class SalariesRepository extends BaseRepository<Salary, SalaryDAO> {

    public SalariesRepository() {
        super(new HashMap<>(), new SalaryDAO());
    }

    public void saveElementAt(int index) {
        modelDAO.save(getElementAt(index));
    }
}
