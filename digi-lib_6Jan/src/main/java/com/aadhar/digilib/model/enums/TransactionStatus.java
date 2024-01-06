package com.aadhar.digilib.model.enums;

public enum TransactionStatus {
    // so this is also an enum
    // what can be status for our Txns ??

    PENDING,
    SUCCESS,
    FAILED

    // so we can have these three as Status for our txns , now lets go back to Transaction table and tell hibernate that whether we want to store
    // the integer value of these enums or the string value . Each enum will have ordinal (integer) value and a string value.
}
