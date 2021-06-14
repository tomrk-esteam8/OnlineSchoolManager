package pl.tomaszrarok.osm.repository;

import lombok.extern.slf4j.Slf4j;
import pl.tomaszrarok.osm.model.Payment;

import java.util.HashMap;

@Slf4j
public class PaymentsRepository extends BaseRepository<Payment, PaymentDAO>{

    public PaymentsRepository() {
        super(new HashMap<>(), new PaymentDAO());
    }

    public void saveElementAt(int index) {
        modelDAO.save(getElementAt(index));
    }
}
