package com.deh.lumen.core_data.security

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.deh.lumen.core_data.CoreDataConstants.KEYSTORE_PROVIDER
import com.deh.lumen.core_data.CoreDataConstants.KEY_ALIAS
import com.deh.lumen.core_data.CoreDataConstants.PASSPHRASE_LENGTH
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import java.security.KeyStore
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject
import javax.inject.Singleton

val Context.passphraseDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "lumen_passphrase"
)

@Singleton
class PassphraseManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val KEY_ENCRYPTED_PASSPHRASE = stringPreferencesKey("encrypted_passphrase")
        private val KEY_ENCRYPTED_IV = stringPreferencesKey("encrypted_iv")
    }

    private val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER).apply {
        load(null)
    }

    suspend fun getOrCreatePassphrase(): ByteArray {
        val prefs = context.passphraseDataStore.data.first()
        val encryptedPassphrase = prefs[KEY_ENCRYPTED_PASSPHRASE]
        val encryptedIv = prefs[KEY_ENCRYPTED_IV]

        if (encryptedPassphrase != null && encryptedIv != null) {
            return decrypt(
                encrypted = Base64.decode(encryptedPassphrase, Base64.NO_WRAP),
                iv = Base64.decode(encryptedIv, Base64.NO_WRAP)
            )
        }

        return generateAndStorePassphrase()
    }

    private suspend fun generateAndStorePassphrase(): ByteArray {
        val passphrase = ByteArray(PASSPHRASE_LENGTH).also { bytes ->
            SecureRandom().nextBytes(bytes)
        }

        val (encrypted, iv) = encrypt(passphrase)

        context.passphraseDataStore.edit { prefs ->
            prefs[KEY_ENCRYPTED_PASSPHRASE] = Base64.encodeToString(encrypted, Base64.NO_WRAP)
            prefs[KEY_ENCRYPTED_IV]         = Base64.encodeToString(iv, Base64.NO_WRAP)
        }

        return passphrase
    }

    private fun encrypt(plaintext: ByteArray): Pair<ByteArray, ByteArray> {
        val key        = getOrCreateKeystoreKey()
        val cipher     = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val iv         = cipher.iv
        val ciphertext = cipher.doFinal(plaintext)
        return Pair(ciphertext, iv)
    }

    private fun decrypt(encrypted: ByteArray, iv: ByteArray): ByteArray {
        val key    = getOrCreateKeystoreKey()
        val spec   = GCMParameterSpec(128, iv)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, key, spec)
        return cipher.doFinal(encrypted)
    }

    private fun getOrCreateKeystoreKey(): SecretKey {
        keyStore.getKey(KEY_ALIAS, null)?.let {
            return it as SecretKey
        }

        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            KEYSTORE_PROVIDER
        )

        keyGenerator.init(
            KeyGenParameterSpec.Builder(
                KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(256)
                .build()
        )

        return keyGenerator.generateKey()
    }
}