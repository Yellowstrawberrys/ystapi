package cf.ystapi.jda.Exceptions;

/**
 * Command Already Exists Exception is calling when Command is Exists!
 * **/

public class CommandAlreadyExistsException extends RuntimeException {
    public CommandAlreadyExistsException(){
        super();
    }

    public CommandAlreadyExistsException(String message){
        super(message);
    }
}
