package pl.psvm;

public class ItemProperties<T, R> {
    T value;
    R weight;

    public ItemProperties(T value, R weight) {
        this.value = value;
        this.weight = weight;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public R getWeight() {
        return weight;
    }

    public void setWeight(R weight) {
        this.weight = weight;
    }
}
