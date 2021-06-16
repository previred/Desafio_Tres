package desafio.enumerators;

public enum ConstantesMsg {

    ACCESS_GRANTED("ACABA DE ACCEDER A LA URL "),
    ACCESS_REDIRECTED("REDIRECCIONADO A LA URL "),
    ACCESS_DENIED("LA URL PROPICIADA NO EXISTE ");

    /**
     * Valor ENUM.
     */
    private final String text;

    /**
     * Constructor.
     *
     * @param text
     */
    private ConstantesMsg(final String text) {
        this.text = text;
    }

    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return this.text;
    }

}
