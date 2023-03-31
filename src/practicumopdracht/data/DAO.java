package practicumopdracht.data;

import java.util.ArrayList;

abstract class DAO<T> {
    ArrayList<T> objects;

    protected DAO() {
        this.objects = new ArrayList<>();
    }

    public ArrayList<T> getAll() {
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
