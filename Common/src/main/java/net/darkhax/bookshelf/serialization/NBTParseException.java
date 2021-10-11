package net.darkhax.bookshelf.serialization;

public class NBTParseException extends RuntimeException {

    public NBTParseException(String msg) {

        super(msg);
    }

    public NBTParseException(String msg, Throwable cause) {

        super(msg, cause);
    }

    public NBTParseException(Throwable cause) {

        super(cause);
    }
}
