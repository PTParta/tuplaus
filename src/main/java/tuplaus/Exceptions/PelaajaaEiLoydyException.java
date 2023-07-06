package tuplaus.Exceptions;

public class PelaajaaEiLoydyException extends Exception {
    public PelaajaaEiLoydyException(String virheviesti) {
        super(virheviesti);
    }
}