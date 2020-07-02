package com.formationandroid.fragments.bdd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memos")
data class MemoDTO(
    @PrimaryKey(autoGenerate = true)
    val memoId: Long = 0,
    val intitule: String? = null)
