package fr.ubordeaux.ddd.examples.infrastructure;

public interface ServiceInterfaceInfrastructureLayer {
    public void connectDatabaseClient(String host, String port);
    public void storeElement(String value);
    public String findElementByValue(String value);
}
