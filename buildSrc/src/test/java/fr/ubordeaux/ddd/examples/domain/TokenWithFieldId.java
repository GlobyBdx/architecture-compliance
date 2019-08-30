package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.fields.EntityID;

public class TokenWithFieldId {
    @EntityID
    private String oneId;
}
