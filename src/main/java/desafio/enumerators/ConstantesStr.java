package desafio.enumerators;

public enum ConstantesStr {

    ORDER_VALOR("VALOR"),
    ORDER_FECHA("FECHA"),
    FORMAT_FECHA("yyyy-MM-dd"),
    URL_FILE("valores.json"),
    SERVER_URL("http://localhost:8010"),
    INIT_URL("/swagger-ui.html"),
    COMMAND_OPEN_CHROME("cmd /c start chrome.exe " + SERVER_URL.text + INIT_URL.text);

    /**
     * Valor ENUM.
     */
    private final String text;

    /**
     * Constructor.
     *
     * @param text
     */
    private ConstantesStr(final String text) {
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
