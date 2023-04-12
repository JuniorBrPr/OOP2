package practicumopdracht.data;

import java.util.ArrayList;

/**
 * Abstract class for DAOs.
 *
 * @param <T> Type of object to store.
 */
abstract class DAO<T> {
    ArrayList<T> objects;

    protected DAO() {
        this.objects = new ArrayList<>();
    }

    /**
     * Gets all objects.
     *
     * @return ArrayList of objects.
     */
    public ArrayList<T> getAll() {
        return this.objects;
    }

    /**
     * Adds or updates an object.
     *
     * @param object Object to add or update.
     */
    public void addOrUpdate(T object) {
        this.objects.stream()
                .filter(obj -> obj.equals(object))
                .findFirst()
                .ifPresentOrElse(obj ->
                                this.objects
                                        .set(this.objects.indexOf(obj), object),
                        () -> this.objects.add(object));
    }

    /**
     * Removes an object.
     *
     * @param object Object to remove.
     */
    public void remove(T object) {
        this.objects.remove(object);
    }

    /**
     * Saves the objects to a file.
     *
     * @return boolean
     */
    public abstract boolean save();

    /**
     * Loads the objects from a file.
     *
     * @return boolean
     */
    public abstract boolean load();
}
