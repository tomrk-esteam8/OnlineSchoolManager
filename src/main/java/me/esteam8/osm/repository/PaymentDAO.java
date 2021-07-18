package me.esteam8.osm.repository;

import me.esteam8.osm.model.Payment;

public class PaymentDAO extends BaseDAO<Payment> {

    @Override
    protected String getQuery() {
        return "select payment from Payment payment";
    }

}
