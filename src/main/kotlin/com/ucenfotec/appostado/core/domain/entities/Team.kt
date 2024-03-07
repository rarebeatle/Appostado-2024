package com.ucenfotec.appostado.core.domain.entities

import com.google.cloud.Timestamp
import com.google.cloud.firestore.DocumentSnapshot
import com.ucenfotec.appostado.core.application.common.exceptions.DocumentSnapshotMissingDataException
import com.ucenfotec.appostado.core.domain.common.BaseEntity
import com.ucenfotec.appostado.core.domain.extensions.getValueOrThrow
import java.util.UUID

data class Team(
    val name: String,
    val photo: String,
    val sportId: String
) : BaseEntity(
    id = UUID.randomUUID().toString(),
    createdAt = null,
    updatedAt = null
) {
    companion object {
        fun fromDocumentSnapshot(documentSnapshot: DocumentSnapshot): Team {
            val data = documentSnapshot.data
                ?: throw DocumentSnapshotMissingDataException(
                    additionalDetails = mapOf("documentSnapshotId" to documentSnapshot.id)
                )

            return Team(
                        name = data.getValueOrThrow<String>("name"),
                        photo = data.getValueOrThrow<String>("photo"),
                        sportId = data.getValueOrThrow<String>("sportId")

            ).apply {
                id = documentSnapshot.id
                createdAt = data.getValueOrThrow<Timestamp>("createdAt")
                updatedAt = data.getValueOrThrow<Timestamp>("updatedAt")
            }
        }
    }
}