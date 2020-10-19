package com.example.propertymanagement.ui.todo

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.propertymanagement.data.models.TodoList
import com.example.propertymanagement.data.models.User
import com.google.firebase.database.*

class  TodoViewModel : ViewModel() {

    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference(TodoList.COLLECTION_NAME).child(
            User.KEY_USER_ID!!
        )

    var mList: ArrayList<TodoList> = ArrayList()

    var title: String? = null
    var description: String? = null

    var todoListener: TodoListener? = null



    fun getData() {
        todoListener?.onStarted()

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.d("DBError", "Error message: " + error.message)
                todoListener?.onFailure(error.message)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mList.clear()
                for (data in dataSnapshot.children) {
                    var todo = data.getValue(TodoList::class.java)

                    mList.add(todo!!)
                }
                todoListener?.onUpdate(mList)
                if(mList.isNullOrEmpty()){
                    todoListener?.onFailure("")
                }
                else
                {
                    Log.d("FirebaseDB", "onSuccess " + mList)
                    todoListener?.onSuccess(mList)

                }

                Log.d("FirebaseDB", "mList inDataChange: $mList")
            }

        })



    }

    fun onButtonAddClicked(view: View) {
        todoListener?.onStarted()

        if (title.isNullOrEmpty() || description.isNullOrEmpty()) {
            todoListener?.onFailure("Please enter title/description")
            return
        } else {
            var todo = TodoList(title!!, description!!)
            var todoId = databaseReference.push().key

            databaseReference.child(todoId!!).setValue(todo)
            todoListener?.onFailure("Inserted")
            todoListener?.onComplete()
        }
    }

}