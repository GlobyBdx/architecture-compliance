package fr.ubordeaux.ddd.examples.infrastructure;

import fr.ubordeaux.ddd.annotations.classes.Service;

@Service
public class ServiceImplementationInfrastructureLayer implements ServiceInterfaceInfrastructureLayer {
    @Override
    public void connectDatabaseClient(String host, String port) {}

    @Override
    public void storeElement(String value) {}

    @Override
    public String findElementByValue(String value) {
        return null;
    }
}
