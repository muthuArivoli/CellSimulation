package configuration.configurationerror;

public class ConfigurationError extends RuntimeException {
    public ConfigurationError(String message){
        super(message);
    }
}
