package pl.tomaszrarok.osm.repository;

import pl.tomaszrarok.osm.model.Payment;

public class PaymentDAO extends BaseDAO<Payment> {

    @Override
    protected String getQuery() {
        return "select payment from Payment payment";
    }

}
