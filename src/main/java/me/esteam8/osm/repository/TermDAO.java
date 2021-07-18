package me.esteam8.osm.repository;

import me.esteam8.osm.model.Term;

public class TermDAO extends BaseDAO<Term> {

    @Override
    protected String getQuery() {
        return "select term from Term term";
    }

}
