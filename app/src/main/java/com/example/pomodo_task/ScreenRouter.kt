package com.example.pomodo_task

class Screen(val route: String) {
    companion object {
        val MY_TASKS = Screen(route = "my_tasks")
        val PERFORMACE = Screen(route = "performace")
        val CREATE_TASK = Screen(route = "create_task")
        val CATEGORIES = Screen(route = "categories")

    }
}