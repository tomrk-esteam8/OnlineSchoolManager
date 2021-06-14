package pl.tomaszrarok.osm.repository;

import pl.tomaszrarok.osm.model.Term;

public class TermDAO extends BaseDAO<Term> {

    @Override
    protected String getQuery() {
        return "select term from Term term";
    }

}
