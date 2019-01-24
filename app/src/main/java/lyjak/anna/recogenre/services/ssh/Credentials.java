package lyjak.anna.recogenre.services.ssh;

class Credentials {

    final String ADDRESS = "51.68.136.131";//"156.17.134.143";
    final String PORT = "60022";
    final String USER = "root";//"student";
    final String PASSWORD = "YjXDhv0C"; //"Ch4ng3M3";

    private static final Credentials ourInstance = new Credentials();

    static Credentials getInstance() {
        return ourInstance;
    }

    private Credentials() {
    }
}
