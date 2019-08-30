package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.classes.Service;

@Service
public class ServiceImplementationDomainLayer implements ServiceInterfaceDomainLayer {
    @Override
    public void performNonNaturalResponsibility() {}
}
