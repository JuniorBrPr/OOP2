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

    public void addOrUpdate(T model) {
        this.objects.stream()
                .filter(object -> object.equals(model))
                .findFirst()
                .ifPresentOrElse(object ->
                                this.objects
                                        .set(this.objects.indexOf(object), model),
                        () -> this.objects.add(model));
    }

    public void remove(T model) {
        this.objects.remove(model);
    }

    public abstract boolean save();
    public abstract boolean load();
}
