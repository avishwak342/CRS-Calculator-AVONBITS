package com.ashish.avonbitscrscalc.model.connectors

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*

/*

    DATASNAPSHOT

 */

val DataSnapshot.getS: (String) -> String
    get() = { this.child(it).value.toString() }


object RealTimeDBConnector {

    private val db = FirebaseDatabase.getInstance().reference

    val getRefToUrl = { url: String -> FirebaseDatabase.getInstance().getReferenceFromUrl(url) }

    val findRef = { childs: List<String> -> continueRef(db, childs) }

    val continueRef = { ref: DatabaseReference, childs: List<String> ->
        var newRef = ref
        for (i in childs) newRef = newRef.child(i)
        newRef
    }

    private val createList = { it: DataSnapshot -> ArrayList<DataSnapshot>().apply { for (ds in it.children) add(ds)} }

    val getMap = { it: DataSnapshot -> mutableMapOf<String, String>().apply { for (i in it.children) this[i.key!!] = i.value.toString()  } }

    val limitedItem = {ref: DatabaseReference, flag: Boolean, limit: Int, unit: (DataSnapshot) -> Unit ->
        addVEL(ref, flag, { it }, unit, limit)
    }

    val getItem = { ref: DatabaseReference, flag: Boolean, unit: (DataSnapshot) -> Unit ->
        addVEL(ref, flag, { it }, unit, null)
    }

    val getListItems = { ref: DatabaseReference, flag: Boolean, unit: (List<DataSnapshot>) -> Unit ->
        addVEL(ref, flag, { createList(it) }, unit, null)
    }

    val getLast = { ref: DatabaseReference, unit: (DataSnapshot) -> Unit ->
        ref.limitToLast(1).addValueEventListener(vel({ it }, unit))
    }

    fun <T>uploadRealTime(ref: DatabaseReference, value: T): Task<Void> { return ref.setValue(value) }

    private fun <T> vel(unit: (DataSnapshot) -> T, unit1: (T) -> Unit): ValueEventListener =
        object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) { Log.i("DataBase Firebase Error", p0.toString()) }
            override fun onDataChange(p0: DataSnapshot) { unit1(unit(p0)) }
        }

    private fun <T> addVEL(ref: DatabaseReference, flag: Boolean, unit: (DataSnapshot) -> T, unit1: (T) -> Unit, limit: Int?) {
        if (flag) if (limit != null) ref.limitToFirst(limit).addValueEventListener(vel(unit, unit1)) else ref.addValueEventListener(vel(unit, unit1))
        else if (limit != null) ref.limitToFirst(10).addListenerForSingleValueEvent(vel(unit, unit1) ) else ref.addListenerForSingleValueEvent(vel(unit, unit1) )
    }

}