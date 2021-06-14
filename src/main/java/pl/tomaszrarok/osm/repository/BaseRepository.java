package pl.tomaszrarok.osm.repository;

import pl.tomaszrarok.osm.model.BaseEntity;

import java.util.List;
import java.util.Map;

public abstract class BaseRepository<M extends BaseEntity, D extends BaseDAO> {
    protected Map<Long, M> data;
    protected D modelDAO;

    public BaseRepository(Map<Long, M> data, D modelDAO) {
        this.data = data;
        this.modelDAO = modelDAO;

        List<M> elements = modelDAO.findAll();
        elements.forEach(e -> data.put(e.getId(), e));
    }

    public M getElementAt(int index) {
        return data.get(getKeyAt(index));
    }

    private Long getKeyAt(int index) {
        return data.keySet().toArray(new Long[data.size()])[index];
    }

    public void addElement(String firstname, String lastname, String email) {

    }

    public int size() {
        return data.size();
    }

    public void removeElementAt(int index) {
        M c = getElementAt(index);
        modelDAO.delete(c.getId());
        data.remove(getKeyAt(index));
    }
}
