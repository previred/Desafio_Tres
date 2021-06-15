package desafio.enumerators;

public enum ConstantesNum {

    MAX_LENGTH(100);

    /**
     * Valor ENUM.
     */
    private final int value;

    /**
     * Constructor.
     *
     * @param value
     */
    private ConstantesNum(final int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
