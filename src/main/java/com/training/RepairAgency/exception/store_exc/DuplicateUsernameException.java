package com.training.RepairAgency.exception.store_exc;

import com.training.RepairAgency.exception.StoreException;

public class DuplicateUsernameException extends StoreException {
    public DuplicateUsernameException(String message) {
        super(message);
    }
}

