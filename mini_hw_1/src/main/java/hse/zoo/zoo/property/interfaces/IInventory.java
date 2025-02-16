package hse.zoo.zoo.property.interfaces;

/**
 * Интерфейс для всего подлежащего инвентаризации
 */
public interface IInventory {
    /**
     * Функция для получения инвентаризационного номера
     * @return инвентаризационный номер
     */
    int getNumber();

    /**
     * Функция для определения инвентаризационного номера
     * @param Number инвентаризационный номер
     */
    void setNumber(int Number);
}
