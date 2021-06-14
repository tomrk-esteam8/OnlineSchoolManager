package pl.tomaszrarok.osm.repository;

import lombok.extern.slf4j.Slf4j;
import pl.tomaszrarok.osm.model.Term;

import java.util.HashMap;

@Slf4j
public class TermsRepository extends BaseRepository<Term, TermDAO> {

    public TermsRepository() {
        super(new HashMap<>(), new TermDAO());
    }

    public void saveElementAt(int index) {
        modelDAO.save(getElementAt(index));
    }
}
