package com.scto.layoutstudio.core.firestore

import com.google.firebase.firestore.FirebaseFirestore

/**
 * Created on Jul 30, 2025.
 *
 */

internal const val DATABASE_NAME = "layoutedito-db"

object FirestoreHelper {
    fun getInstance() = FirebaseFirestore.getInstance(DATABASE_NAME)
}