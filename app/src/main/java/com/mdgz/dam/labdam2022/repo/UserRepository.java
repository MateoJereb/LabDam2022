package com.mdgz.dam.labdam2022.repo;

import java.util.UUID;

public class UserRepository {
    private static final UUID CURRENT = UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3");

    public static UUID currentUserId() {
        return CURRENT;
    }
}
