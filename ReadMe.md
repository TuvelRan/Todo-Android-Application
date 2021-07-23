## About
This is a simple todo list application made in Android Studio, written in Kotlin.

## How-It-Works
Saving the todo list to a Shared Preferences instance. Gson was used to parse the list to a json format.

Saving and Loading functions:
```kotlin
fun saveTodoListData() {
    val json = Gson().toJson(todoList)
    sp.edit().putString("todoList", json).apply()
    if (todoList.size > 0)
        noItemsLayout.visibility = View.INVISIBLE
    else
        noItemsLayout.visibility = View.VISIBLE
}

fun loadTodoListData(): ArrayList<TODO> {
    val json = sp.getString("todoList", null)
    if (json != null)
        return Gson().fromJson(json, object : TypeToken<ArrayList<TODO>>() {}.type)
    return ArrayList<TODO>()
}
```

**Everyone is free to use the code in this source-code for their own applications.**
