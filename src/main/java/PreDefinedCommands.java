public enum PreDefinedCommands {
    EXIT_0("exit"),
    ECHO("echo"),
    TYPE("type"),;

    private String value;
    PreDefinedCommands(String s) {
        this.value = s;
    }
    public String getValue() {
        return value;
    }

    public static PreDefinedCommands fromString(String s){
        for(PreDefinedCommands c : PreDefinedCommands.values()){
            if(c.value.equals(s)){
                return c;
            }
        }
        return null;
    }
}
