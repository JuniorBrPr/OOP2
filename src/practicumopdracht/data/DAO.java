package practicumopdracht.data;

import java.util.ArrayList;
import java.util.List;

public abstract class DAO<T> {
    final List<T> objects;

    protected DAO() {
        this.objects = new ArrayList<>();
    }

    public List<T> getAll() {
        return this.objects;
    }

    public void addOrUpdate(T object) {
        this.objects.stream()
                .filter(obj -> obj.equals(object))
                .findFirst()
                .ifPresentOrElse(obj ->
                                this.objects
                                        .set(this.objects.indexOf(obj), object),
                        () -> this.objects.add(object));
    }

    public void remove(T object) {
        this.objects.remove(object);
    }

    public abstract boolean save();
    public abstract boolean load();
}
