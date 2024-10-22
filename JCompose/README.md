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
2. Display the list in the "Shop" tab using LazyColumn in the HorizontalPager.


###### Explanation:
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
```

# REST APIS
RESTful APIs **(Representational State Transfer)** are web services that adhere to the constraints and principles of REST, 
an architectural style for designing networked applications. They use HTTP requests to perform standard CRUD **(Create, Read, Update, Delete)** 
operations on resources, typically represented in formats like JSON or XML.

Key Concepts in RESTful APIs:
Resources:

Everything in a RESTful API is considered a resource (e.g., a user, a product, a file).
Resources are identified by URIs (Uniform Resource Identifiers). For example:
sql
Copy code
GET /users/123 - Fetch user with ID 123
HTTP Methods: REST APIs rely on standard HTTP methods to perform actions on resources:

GET: Retrieve data from the server (Read).
POST: Send data to the server to create a new resource (Create).
PUT: Update an existing resource (Update).
DELETE: Remove a resource (Delete).
PATCH: Partially update a resource.


# Key Concepts in RESTful API Integration for Android
HTTP Client: Android apps use HTTP clients to make network requests. Some common options are:

**HttpURLConnection:** A built-in class to send and receive HTTP requests. It's not recommended for modern Android apps 
because it's low-level and requires more manual handling.

**Retrofit:** A modern library that simplifies the process of making HTTP requests and handling JSON responses.

**OkHttp:** Another widely used HTTP client that works well with Retrofit.
JSON Parsing: APIs typically return data in JSON format. Libraries like Gson or Moshi are used to convert JSON into Java/Kotlin objects.

**Concurrency:** Network requests should never be performed on the main thread, as it can lead to a blocked UI and ANRs (Application 
Not Responding errors). You can use tools like:

**Coroutines (recommended for Kotlin)**
RxJava for reactive programming
AsyncTask (deprecated but still used in older apps)
Lifecycle Awareness: Since API calls might return after the activity is destroyed, Android provides lifecycle-aware components such
as LiveData and ViewModel to handle responses and UI updates safely.

## **Tools for RESTful API Integration in Android**
1. **Retrofit** _(Highly recommended)_
   Retrofit is the most popular library for making HTTP requests in Android. It simplifies the process by converting HTTP API calls into Java/Kotlin interfaces.

### Step to use Retrofit:
1. Add Retrofit Dependency

` implementation 'com.squareup.retrofit2:retrofit:2.9.0' `

    ` implementation 'com.squareup.retrofit2:converter-gson:2.9.0'`

2. Define API Interface
3. Initialize Retrofit in a Singleton
4. use Coroutines to make API Calls
5. Observe data in your Activity or Fragment.

# Summary
**Retrofit** is the most efficient way to handle API calls in Android.
**Coroutines** make asynchronous operations easier to manage in Kotlin.
Use **_ViewModel_** and **_LiveData_** to handle API responses in a lifecycle-aware manner.
Handle authentication, error handling, and JSON parsing effectively in your API calls.

# When do we Apply Retrofit?

Let’s say you are developing an e-commerce Android app where users can browse a list of products, view details about a product, 
and make a purchase. To achieve this, you need a way to fetch data (e.g., product details, prices, user accounts) from a remote server and 
display it in your app. This is where Retrofit and RESTful APIs come into play.

How Retrofit fits in:
Fetching Product List: When the user opens the app, the product data (name, price, images) will be retrieved from a server. Retrofit will be
used to send an HTTP request to a RESTful API endpoint (e.g., https://api.example.com/products). The server will respond with JSON data, and 
Retrofit will parse it into Kotlin objects.

Fetching Product Details: When a user clicks on a product to view more details, such as specifications, reviews, and available stock, Retrofit
will make another request to the API, like https://api.example.com/products/{id}. This returns detailed data for the selected product.

User Authentication: If your app has user accounts, users will need to sign in or register. Retrofit can be used to send login credentials to the API,
and in return, you’ll get a token that authenticates future requests (for example, placing an order).

Placing an Order: Once users add items to their cart and proceed to checkout, the order details (such as selected products and shipping information)
will be sent to the server using Retrofit. This data could be sent via a POST request to an endpoint like https://api.example.com/orders.

In each of these cases, Retrofit manages the API communication, converts the API’s response (usually in JSON format) into usable Kotlin objects,
and handles error responses.