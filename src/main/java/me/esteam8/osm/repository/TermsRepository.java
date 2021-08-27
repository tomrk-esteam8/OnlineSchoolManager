package me.esteam8.osm.repository;

import lombok.extern.slf4j.Slf4j;
import me.esteam8.osm.model.Term;

import java.util.HashMap;
import java.util.TreeMap;

@Slf4j
public class TermsRepository extends BaseRepository<Term, TermDAO> {

    public TermsRepository() {
        super(new TreeMap<>(), new TermDAO());
    }

    public void saveElementAt(int index) {
        modelDAO.save(getElementAt(index));
    }
}
