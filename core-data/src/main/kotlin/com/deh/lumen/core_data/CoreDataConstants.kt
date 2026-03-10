package com.deh.lumen.core_data

object CoreDataConstants {
    const val USER_TABLE_NAME = "user"
    const val CHECK_IN_TABLE_NAME = "check_in"

    const val DATABASE_NAME = "lumen.db"
    const val DATABASE_VERSION = 1

    const val KEYSTORE_PROVIDER = "AndroidKeyStore"
    const val KEY_ALIAS = "lumen_db_key"
    const val PREFS_NAME = "lumen_secure_prefs"
    const val PREFS_KEY = "lumen_db_passphrase"
    const val PASSPHRASE_LENGTH = 32 // bytes
}
