package hse.zoo.zoo.property.interfaces;

/**
 * Интерфейс для всех живых существ
 */
public interface IAlive {
    /**
     * Функция объема потребления пищи данным существом
     * @return объем пищи (целое число кг)
     */
    int getFood();

    /**
     * Функция переопределения нормы пищи для данного существа
     * @param food объем пищи (целое число кг)
     */
    void setFood(int food);
}
