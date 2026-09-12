public interface CellFactory<T extends Cell> {
    T create(boolean alive);
}
