 # Compose
Has its foundations  of Jetpack Compose to Advanced levels.
Though currently, the apps still has basic stuff.Stil working on it.
Advance levels 

# What are Jetpack Libraries?
Jetpack Libraries are a suite of Android components and tools provided by Google to help developers build high-quality apps quickly and efficiently. These libraries focus on solving common problems, ensuring backward compatibility, and simplifying complex tasks.mmmm
Jetpack is divided into four categories: 
Foundation, 
Architecture,
Behavior, and
UI.

**Categories of Jetpack Libraries**

**1. Foundation**
These libraries provide core functionalities and tools for building apps, such as backward compatibility and testing.
Examples:

AppCompat: Ensures backward compatibility for newer UI elements on older Android versions.

Android KTX: Kotlin extensions that simplify Android code.

Multidex: Helps manage multiple DEX files for apps with large codebases.

Test libraries: Frameworks for writing unit and UI tests.

**2. Architecture**

These libraries help implement MVVM and other architecture patterns to build maintainable and scalable apps.
Examples:

ViewModel: Stores UI-related data across configuration changes (like screen rotations).

LiveData: An observable data holder class that updates the UI automatically.

Room: An SQLite-based ORM library for easier database management.

DataStore: A replacement for SharedPreferences, optimized for reactive programming.

WorkManager: Manages background tasks and ensures they run reliably.

**3. Behavior**

These libraries support common app behaviors such as notifications and permissions.
Examples:

Navigation: Manages in-app navigation and supports deep linking.

Permissions: Simplifies handling runtime permissions.

Notifications: Helps create consistent notifications across different devices.

**4. UI**

These libraries simplify the creation of user interfaces and enhance the look and feel of apps.
Examples:

ConstraintLayout: A flexible layout for designing complex UIs.

Compose UI: Google's modern toolkit for building native UI with declarative components.

Fragment: Allows the use of multiple screens within a single activity.

Paging: Efficiently loads and displays large datasets in chunks.


**Advantages of Jetpack Libraries**

Backward Compatibility: Works across different Android versions.

Modular: You can use only the libraries you need.

Faster Development: Simplifies common tasks and reduces boilerplate code.

Lifecycle Awareness: Integrates lifecycle-aware components for better memory management.


Jetpack simplifies modern Android development and promotes the use of best practices like MVVM, Kotlin-first APIs, and declarative UI with Jetpack Compose.

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
items using LazyColumn and displays the names.Lazycolumn is an alternative to recyclerrview.


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

I tried towork on MVVM but it has given me challenges. The Livedata led to erros  and it has costed me alot of time, but thi is the fun of programming, LEarning through errors.

Working with LiveData and MVVM can sometimes be challenging, especially when dealing with dependencies or debugging issues. If LiveData has been causing persistent issues, it is good to consider switching to StateFlow or MutableState for state management in Jetpack Compose. These are more Compose-friendly and often simpler to work with.




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

2. Define API Interface (import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("users")
    Call<List<User>> getUsers();
}
)
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

#### How Retrofit fits in:
**Fetching Product List**: When the user opens the app, the product data (name, price, images) will be retrieved from a server. Retrofit will be
used to send an HTTP request to a RESTful API endpoint (e.g., https://api.example.com/products). The server will respond with JSON data, and 
Retrofit will parse it into Kotlin objects.

**Fetching Product Details**: When a user clicks on a product to view more details, such as specifications, reviews, and available stock, Retrofit
will make another request to the API, like https://api.example.com/products/{id}. This returns detailed data for the selected product.

**User Authentication:** If your app has user accounts, users will need to sign in or register. Retrofit can be used to send login credentials to the API,
and in return, you’ll get a token that authenticates future requests (for example, placing an order).

**Placing an Order**: Once users add items to their cart and proceed to checkout, the order details (such as selected products and shipping information)
will be sent to the server using Retrofit. This data could be sent via a POST request to an endpoint like https://api.example.com/orders.

In each of these cases, Retrofit manages the API communication, converts the API’s response (usually in JSON format) into usable Kotlin objects,
and handles error responses.

Retrofit has to apply MVVM in it's design and workflow. Retrofit has to fetch data from the server and display them to the user. This is the reason it is important to understand MVVM first so that Retrofit implementation will be easier.


Do You Need MVVM for Retrofit?

No, you can use Retrofit without MVVM for simple apps.

Yes, for more complex apps, MVVM helps you manage network responses effectively


This week, i majorly worked on FTk.
FTK -Forensic Toolkit.

Retrofit: A Powerful HTTP Client for Android

Retrofit is a type-safe HTTP client for Android and Java developed by Square. It simplifies network requests by converting API responses directly into Kotlin or Java objects.
