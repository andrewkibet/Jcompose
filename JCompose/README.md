# Compose
Starting from the basics of Compose to advanced. <br>
Radio Button.

**Modifiers**  https://developer.android.com/jetpack/compose/modifiers#:~:text=Modifiers%20allow%20you%20to%20decorate,Add%20information%2C%20like%20accessibility%20labels <br> 
Allow you to augment or decorate Composables. It lets you do these  sorts of things: <br>
  1. Change the composable size, layout, shape, layout, behavior and appearance. <br>
  2. Add information like accessibility labels. <br>
  3. Process user input. <br>
  4. Add high-level interactions, like making an element clickable, scrollable, draggable, or zoomable. <br>

* Order of Compose functions matters. <br> <br>

If you want to access colors from resources in Jetpack Compose without declaring variables for each color, <br>
you can do so by using the colorResource function directly in your Composable functions. <br>
_color = colorResource(id = R.color.black)_

### Adding a list of names to the shop tab:
To add a list of items (names) to the "Shop" tab, we can modify the HorizontalPager content for the "Shop" tab to 
display a list of items using a LazyColumn.

Steps to Add Items to the "Shop" Tab:
1. Create a list of items for the shop.
Display the list in the "Shop" tab using LazyColumn in the HorizontalPager.


Explanation:
ShopTab Composable: This function creates a list of 
items using LazyColumn and displays the names.


Other Topics:
1. Understanding Architecture Patterns: MVVM
2. Networking -RETROFIT
3. Local Databases RESTful APIs
4. API Communication
5. State Management

#  **MVVM**   

   **Understanding the Components**
1. **Model**:Represents the data and business logic of the application.
   It interacts with the database, network, and other data sources.
   Contains data classes and repository classes to manage data.
2. **View:**
   Represents the UI of the application.
   In Jetpack Compose, this is defined using composable functions.
   Observes data from the ViewModel and displays it to the user.

3. **ViewModel**:Acts as a bridge between the Model and the View.
   Holds UI-related data and handles business logic.
   Survives configuration changes (like screen rotations) without losing data.

## Setting Up MVVM in Jetpack Compose
#### Step 1: Add Dependencies
`dependencies {
implementation "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0"
implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.5.0"
implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.5.0"
implementation "androidx.room:room-runtime:2.5.0"
kapt "androidx.room:room-compiler:2.5.0"
// Other dependencies...
}`

#### Step 2: Define the Model

Create a data class and a repository.
For example, let's say you have a simple app that manages a list of tasks.

```// Task.kt (Model)
data class Task(val id: Int, val name: String)

// TaskRepository.kt
class TaskRepository {
private val tasks = mutableListOf<Task>()```

    fun getTasks(): List<Task> {
        return tasks
    }

    fun addTask(task: Task) {
        tasks.add(task)
    }
}
